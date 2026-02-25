import java.util.Random;
import java.util.Arrays;

public class AlgorithmPerformanceAnalyzer {

    // Linear Search Implementation
    
    public static int linearSearch(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) return i;
        }
        return -1;
    }

    // Binary Search Implementation

    public static int binarySearch(int[] arr, int key) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == key) return mid;
            if (arr[mid] < key) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    // Generate Random Array
    
    public static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(10000);
        }
        return arr;
    }

    // Performance Analyzer
    
    public static void analyzePerformance() {
        int[] sizes = {100, 500, 1000};

        System.out.println("\n===========================================================================");
        System.out.println("                Algorithm Performance Analyzer (Time in ns)");
        System.out.println("===========================================================================");
        System.out.printf("%-10s %-20s %-20s %-20s\n", "Size", "Quick Sort", "Linear Search", "Binary Search");
        System.out.println("---------------------------------------------------------------------------");

        for (int size : sizes) {
            int[] data = generateRandomArray(size);
            int[] sortCopy = Arrays.copyOf(data, data.length);

            // Measure Quick Sort Time
            long start = System.nanoTime();
            SortingAlgorithms.quickSort(sortCopy, 0, sortCopy.length - 1);
            long quickSortTime = System.nanoTime() - start;

            // Search for an element (use the sorted copy for binary search)
            int key = sortCopy[size / 2]; 

            // Measure Linear Search Time
            start = System.nanoTime();
            linearSearch(data, key);
            long linearSearchTime = System.nanoTime() - start;

            // Measure Binary Search Time
            start = System.nanoTime();
            binarySearch(sortCopy, key);
            long binarySearchTime = System.nanoTime() - start;

            System.out.printf("%-10d %-20d %-20d %-20d\n", size, quickSortTime, linearSearchTime, binarySearchTime);
        }
        System.out.println("===========================================================================\n");
    }
}
