import java.util.*;
public class Main {

    private static final CityBST cityTree = new CityBST();
    private static final Graph graph = new Graph();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n************ SMART CITY INTEGRATED SYSTEM ************");
            System.out.println("1. Module 1: Smart City Route Planner");
            System.out.println("2. Module 2: Data Sorter Comparison Tool");
            System.out.println("3. Module 3: Algorithm Performance Analyzer");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = readInt(sc);
            switch (choice) {
                case 1 -> module1Menu(sc);
                case 2 -> SortingModule.run();
                case 3 -> AlgorithmPerformanceAnalyzer.analyzePerformance();
                case 4 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void module1Menu(Scanner sc) {
        while (true) {
            System.out.println("\n--- Module 1: Route Planner ---");
            System.out.println("1. Add New Location (City)");
            System.out.println("2. Remove Location (City)");
            System.out.println("3. Add Road (Connect Two Cities)");
            System.out.println("4. Remove Road (Disconnect Two Cities)");
            System.out.println("5. Display Registered Cities (BST Order)");
            System.out.println("6. Display All Road Connections (Graph)");
            System.out.println("7. Find Route (BFS - Queue, shortest by edges)");
            System.out.println("8. Traverse From City (DFS - Stack)");
            System.out.println("9. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = readInt(sc);
            switch (choice) {
                case 1 -> addCity(sc);
                case 2 -> removeCity(sc);
                case 3 -> addRoad(sc);
                case 4 -> removeRoad(sc);
                case 5 -> displayCities();
                case 6 -> displayGraph();
                case 7 -> findRouteBFS(sc);
                case 8 -> traverseDFS(sc);
                case 9 -> { return; }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addCity(Scanner sc) {
        System.out.print("Enter city name: ");
        String city = readNonEmpty(sc).toLowerCase();

        if (cityTree.contains(city)) {
            System.out.println("City already exists.");
            return;
        }

        cityTree.insert(city);
        graph.addVertex(city);
        System.out.println("City added: " + city);
    }

    private static void removeCity(Scanner sc) {
        System.out.print("Enter city name to remove: ");
        String city = readNonEmpty(sc).toLowerCase();

        if (!cityTree.contains(city)) {
            System.out.println("City not found.");
            return;
        }

        cityTree.delete(city);
        graph.removeVertex(city);
        System.out.println("City removed: " + city);
    }

    private static void addRoad(Scanner sc) {
        System.out.print("Enter start city: ");
        String a = readNonEmpty(sc).toLowerCase();
        System.out.print("Enter end city: ");
        String b = readNonEmpty(sc).toLowerCase();

        if (!cityTree.contains(a) || !cityTree.contains(b)) {
            System.out.println("Both cities must exist before adding a road.");
            return;
        }
        if (a.equals(b)) {
            System.out.println("Cannot connect a city to itself.");
            return;
        }

        graph.addEdge(a, b);
        System.out.println("Connected " + a + " and " + b);
    }

    private static void removeRoad(Scanner sc) {
        System.out.print("Enter start city: ");
        String a = readNonEmpty(sc).toLowerCase();
        System.out.print("Enter end city: ");
        String b = readNonEmpty(sc).toLowerCase();

        if (!graph.hasVertex(a) || !graph.hasVertex(b)) {
            System.out.println("Both cities must exist in the graph.");
            return;
        }

        boolean removed = graph.removeEdge(a, b);
        System.out.println(removed ? ("Disconnected " + a + " and " + b) : "No road existed between them.");
    }

    private static void displayCities() {
        System.out.println("\n--- Registered Cities (BST In-Order / Alphabetical) ---");
        List<String> cities = cityTree.inOrder();
        if (cities.isEmpty()) {
            System.out.println("(no cities)");
        } else {
            for (String c : cities) System.out.println("- " + c);
        }
    }

    private static void displayGraph() {
        System.out.println("\n--- Road Connections (Graph Adjacency List) ---");
        graph.printAdjacencyList();
    }

    private static void findRouteBFS(Scanner sc) {
        System.out.print("Enter start city: ");
        String start = readNonEmpty(sc).toLowerCase();
        System.out.print("Enter end city: ");
        String end = readNonEmpty(sc).toLowerCase();

        if (!graph.hasVertex(start) || !graph.hasVertex(end)) {
            System.out.println("Both cities must exist.");
            return;
        }

        List<String> path = graph.bfsShortestPath(start, end);
        if (path.isEmpty()) {
            System.out.println("No route found between " + start + " and " + end);
        } else {
            System.out.println("Route (BFS): " + String.join(" -> ", path));
        }
    }

    private static void traverseDFS(Scanner sc) {
        System.out.print("Enter start city: ");
        String start = readNonEmpty(sc).toLowerCase();

        if (!graph.hasVertex(start)) {
            System.out.println("City must exist.");
            return;
        }

        List<String> order = graph.dfsTraversal(start);
        System.out.println("DFS Traversal (Stack) from " + start + ": " + String.join(" -> ", order));
    }

    private static int readInt(Scanner sc) {
        while (true) {
            String s = sc.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.print("Enter a valid number: ");
            }
        }
    }

    private static String readNonEmpty(Scanner sc) {
        while (true) {
            String s = sc.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.print("Input cannot be empty. Try again: ");
        }
    }

    static class CityBST {
        static class Node {
            String key;
            Node left, right;
            Node(String key) { this.key = key; }
        }

        private Node root;

        public boolean contains(String key) {
            Node cur = root;
            while (cur != null) {
                int cmp = key.compareTo(cur.key);
                if (cmp == 0) return true;
                cur = (cmp < 0) ? cur.left : cur.right;
            }
            return false;
        }

        public void insert(String key) {
            root = insertRec(root, key);
        }

        private Node insertRec(Node node, String key) {
            if (node == null) return new Node(key);
            int cmp = key.compareTo(node.key);
            if (cmp < 0) node.left = insertRec(node.left, key);
            else if (cmp > 0) node.right = insertRec(node.right, key);
            return node;
        }

        public void delete(String key) {
            root = deleteRec(root, key);
        }

        private Node deleteRec(Node node, String key) {
            if (node == null) return null;

            int cmp = key.compareTo(node.key);
            if (cmp < 0) node.left = deleteRec(node.left, key);
            else if (cmp > 0) node.right = deleteRec(node.right, key);
            else {
                if (node.left == null) return node.right;
                if (node.right == null) return node.left;

                Node succ = minNode(node.right);
                node.key = succ.key;
                node.right = deleteRec(node.right, succ.key);
            }
            return node;
        }

        private Node minNode(Node node) {
            while (node.left != null) node = node.left;
            return node;
        }

        public List<String> inOrder() {
            List<String> out = new ArrayList<>();
            inOrderRec(root, out);
            return out;
        }

        private void inOrderRec(Node node, List<String> out) {
            if (node == null) return;
            inOrderRec(node.left, out);
            out.add(node.key);
            inOrderRec(node.right, out);
        }
    }

    static class Graph {
        private final Map<String, Set<String>> adj = new HashMap<>();

        public void addVertex(String v) {
            adj.putIfAbsent(v, new TreeSet<>());
        }

        public boolean hasVertex(String v) {
            return adj.containsKey(v);
        }

        public void removeVertex(String v) {
            if (!adj.containsKey(v)) return;
            for (String n : adj.get(v)) {
                adj.get(n).remove(v);
            }
            adj.remove(v);
        }

        public void addEdge(String a, String b) {
            addVertex(a);
            addVertex(b);
            adj.get(a).add(b);
            adj.get(b).add(a);
        }

        public boolean removeEdge(String a, String b) {
            if (!adj.containsKey(a) || !adj.containsKey(b)) return false;
            boolean removed1 = adj.get(a).remove(b);
            boolean removed2 = adj.get(b).remove(a);
            return removed1 || removed2;
        }

        public void printAdjacencyList() {
            if (adj.isEmpty()) {
                System.out.println("(no cities/roads)");
                return;
            }

            List<String> cities = new ArrayList<>(adj.keySet());
            Collections.sort(cities);

            for (String city : cities) {
                System.out.println(city + " connects to: " + adj.get(city));
            }
        }

        public List<String> bfsShortestPath(String start, String end) {
            if (start.equals(end)) return List.of(start);

            Queue<String> q = new ArrayDeque<>();
            Map<String, String> parent = new HashMap<>();
            Set<String> visited = new HashSet<>();

            visited.add(start);
            q.add(start);

            while (!q.isEmpty()) {
                String cur = q.poll();
                for (String nxt : adj.getOrDefault(cur, Set.of())) {
                    if (visited.contains(nxt)) continue;
                    visited.add(nxt);
                    parent.put(nxt, cur);
                    if (nxt.equals(end)) {
                        return buildPath(parent, start, end);
                    }
                    q.add(nxt);
                }
            }
            return Collections.emptyList();
        }

        private List<String> buildPath(Map<String, String> parent, String start, String end) {
            LinkedList<String> path = new LinkedList<>();
            String cur = end;
            while (cur != null) {
                path.addFirst(cur);
                if (cur.equals(start)) break;
                cur = parent.get(cur);
            }
            if (!path.isEmpty() && path.getFirst().equals(start)) return path;
            return Collections.emptyList();
        }

        public List<String> dfsTraversal(String start) {
            List<String> order = new ArrayList<>();
            Set<String> visited = new HashSet<>();
            Deque<String> stack = new ArrayDeque<>();
            stack.push(start);

            while (!stack.isEmpty()) {
                String cur = stack.pop();
                if (visited.contains(cur)) continue;
                visited.add(cur);
                order.add(cur);

                List<String> neighbors = new ArrayList<>(adj.getOrDefault(cur, Set.of()));
                Collections.sort(neighbors, Collections.reverseOrder());
                for (String nxt : neighbors) {
                    if (!visited.contains(nxt)) stack.push(nxt);
                }
            }
            return order;
        }
    }
}