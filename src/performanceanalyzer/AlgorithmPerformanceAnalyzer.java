import java.util.Arrays;
import java.util.Random;

public class AlgorithmPerformanceAnalyzer {

    // Linear Search O(n)
    
    public static int linearSearch(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key)
                return i;
        }
        return -1;
    }

    // Binary Search O(log n)
    
    public static int binarySearch(int[] arr, int key) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == key)
                return mid;
            else if (arr[mid] < key)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return -1;
    }

    // Quick Sort O(n log n)
    
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
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

        System.out.println("\n==============================================================");
        System.out.println("     Algorithm Performance Analyzer (Time in nanoseconds)");
        System.out.println("==============================================================");
        System.out.printf("%-10s %-20s %-20s %-20s\n",
                "Size", "Quick Sort", "Linear Search", "Binary Search");
        System.out.println("--------------------------------------------------------------");

        for (int size : sizes) {

            int[] data = generateRandomArray(size);
            int[] sortedData = Arrays.copyOf(data, data.length);

            // Measure Quick Sort
            long start = System.nanoTime();
            quickSort(sortedData, 0, sortedData.length - 1);
            long quickSortTime = System.nanoTime() - start;

            int key = sortedData[size / 2];

            // Measure Linear Search
            start = System.nanoTime();
            linearSearch(data, key);
            long linearTime = System.nanoTime() - start;

            // Measure Binary Search
            start = System.nanoTime();
            binarySearch(sortedData, key);
            long binaryTime = System.nanoTime() - start;

            System.out.printf("%-10d %-20d %-20d %-20d\n",
                    size, quickSortTime, linearTime, binaryTime);
        }

        System.out.println("==============================================================");
    }
}