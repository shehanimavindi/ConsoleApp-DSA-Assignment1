import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class SortingModule {

    private static final Scanner scanner = new Scanner(System.in);
    
    // Menu-driven interface to allow the user to interact with the sorting module
    public static void run() {
        int[] data = null;
        int choice;

        do {
            System.out.println("\n===== Data Sorter – Sorting Algorithm Comparison Tool =====");
            System.out.println("1. Enter numbers manually");
            System.out.println("2. Generate random dataset");
            System.out.println("3. Compare Sorting Algorithms");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> data = enterNumbers();// User enters their own numbers
                case 2 -> data = generateRandomNumbers();// Generate a random dataset
                case 3 -> {
                    if (data == null) {
                        System.out.println("Please enter or generate data first!");
                    } else {
                        compareSortingAlgorithms(data);
                    }
                }
                case 4 -> System.out.println("Exiting module...");
                default -> System.out.println("Invalid choice! Please try again.");
            }

        } while (choice != 4);// Keep looping until user exits
    }

    // Method to manually enter numbers
    private static int[] enterNumbers() {
        System.out.print("How many numbers do you want to enter? ");
        int size = scanner.nextInt();

        int[] arr = new int[size];
        System.out.println("Enter the numbers:");
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }
        return arr;
    }

    // Method to generate random numbers
    private static int[] generateRandomNumbers() {
        System.out.print("Enter dataset size: ");
        int size = scanner.nextInt();

        int[] arr = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(1000); // Random number between 0-999
        }

        System.out.println("Random dataset generated successfully!");
        return arr;
    }

    // Method to compare all sorting algorithms
    private static void compareSortingAlgorithms(int[] originalArray) {
        int[] bubbleArray = Arrays.copyOf(originalArray, originalArray.length);
        int[] mergeArray = Arrays.copyOf(originalArray, originalArray.length);
        int[] quickArray = Arrays.copyOf(originalArray, originalArray.length);

        // Bubble Sort
        long start = System.nanoTime();
        SortingAlgorithms.bubbleSort(bubbleArray);
        long bubbleTime = System.nanoTime() - start;

        // Merge Sort
        start = System.nanoTime();
        SortingAlgorithms.mergeSort(mergeArray, 0, mergeArray.length - 1);
        long mergeTime = System.nanoTime() - start;

        // Quick Sort
        start = System.nanoTime();
        SortingAlgorithms.quickSort(quickArray, 0, quickArray.length - 1);
        long quickTime = System.nanoTime() - start;

        // Display sorted arrays
        System.out.println("\nSorted Output (Bubble Sort):");
        System.out.println(Arrays.toString(bubbleArray));

        System.out.println("\nSorted Output (Merge Sort):");
        System.out.println(Arrays.toString(mergeArray));

        System.out.println("\nSorted Output (Quick Sort):");
        System.out.println(Arrays.toString(quickArray));

        // Display execution times
        System.out.println("\nAlgorithm Performance Comparison (in nanoseconds)");
        System.out.println("-------------------------------------------------");
        System.out.println("Bubble Sort : " + bubbleTime + " ns");
        System.out.println("Merge Sort  : " + mergeTime + " ns");
        System.out.println("Quick Sort  : " + quickTime + " ns");
    }
}

