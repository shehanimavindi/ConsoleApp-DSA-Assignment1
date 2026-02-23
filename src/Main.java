import java.util.Random;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

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

                case 1 -> data = enterNumbers();

                case 2 -> data = generateRandomNumbers();

                case 3 -> {
                    if (data == null) {
                        System.out.println("Please enter or generate data first!");
                    } else {
                        DataSorter.compareSortingAlgorithms(data);
                    }
                }

                case 4 -> System.out.println("Exiting program...");

                default -> System.out.println("Invalid choice! Please try again.");
            }

        } while (choice != 4);
    }

    // Method to manually enter numbers
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

    // Method to generate random numbers
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
