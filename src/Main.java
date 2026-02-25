import java.util.Random;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int mainChoice;

        do {
            System.out.println("\n=============================================");
            System.out.println("    Smart City Integrated Application");
            System.out.println("=============================================");
            System.out.println("1. Module 1: Smart City Route Planner");
            System.out.println("2. Module 2: Data Sorter Comparison Tool");
            System.out.println("3. Module 3: Algorithm Performance Analyzer");
            System.out.println("4. Exit");
            System.out.print("Select a module to enter: ");

            mainChoice = scanner.nextInt();

            switch (mainChoice) {
                case 1 -> SySystem.err.println("Module 01 not yet implemented");
                case 2 -> module2Menu();
                case 3 -> AlgorithmPerformanceAnalyzer.analyzePerformance();
                case 4 -> System.out.println("Exiting application. Goodbye!");
                default -> System.out.println("Invalid selection!");
            }
        } while (mainChoice != 4);
    }

    // Module 2 Sub-Menu (Developed by Member 2)
    
    private static void module2Menu() {
        int[] data = null;
        int choice;

        do {
            System.out.println("\n--- Module 2: Data Sorter Tool ---");
            System.out.println("1. Enter numbers manually");
            System.out.println("2. Generate random dataset");
            System.out.println("3. Compare Sorting Algorithms");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choice: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> data = enterNumbers();
                case 2 -> data = generateRandomNumbers();
                case 3 -> {
                    if (data == null) {
                        System.out.println("Please enter or generate data first!");
                    } else {
                        DataSorter.compareSortingAlgorithms(data);
                    }
                }
                case 4 -> System.out.println("Returning...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 4);
    }

    public static int[] enterNumbers() {
        System.out.print("How many numbers do you want to enter? ");
        int size = scanner.nextInt();
        int[] arr = new int[size];
        System.out.println("Enter the numbers:");
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }
        return arr;
    }

    public static int[] generateRandomNumbers() {
        System.out.print("Enter dataset size: ");
        int size = scanner.nextInt();
        int[] arr = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(1000);
        }
        System.out.println("Random dataset generated successfully!");
        return arr;
    }
}