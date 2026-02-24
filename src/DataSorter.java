import java.util.Arrays;

public class DataSorter {

    public static void compareSortingAlgorithms(int[] originalArray) {

        int[] bubbleArray = Arrays.copyOf(originalArray, originalArray.length);
        int[] mergeArray = Arrays.copyOf(originalArray, originalArray.length);
        int[] quickArray = Arrays.copyOf(originalArray, originalArray.length);

        // Bubble Sort Time
        long start = System.nanoTime();
        SortingAlgorithms.bubbleSort(bubbleArray);
        long bubbleTime = System.nanoTime() - start;

        // Merge Sort Time
        start = System.nanoTime();
        SortingAlgorithms.mergeSort(mergeArray, 0, mergeArray.length - 1);
        long mergeTime = System.nanoTime() - start;

        // Quick Sort Time
        start = System.nanoTime();
        SortingAlgorithms.quickSort(quickArray, 0, quickArray.length - 1);
        long quickTime = System.nanoTime() - start;

        // Display Results
        System.out.println("\nSorted Outputs:");
        System.out.println("Bubble Sorted: " + Arrays.toString(bubbleArray));
        System.out.println("Merge Sorted : " + Arrays.toString(mergeArray));
        System.out.println("Quick Sorted : " + Arrays.toString(quickArray));

        System.out.println("\nAlgorithm Performance Comparison");
        System.out.println("-----------------------------------------");
        System.out.printf("%-20s | %20s\n", "Sorting Algorithm", "Execution Time");
        System.out.println("---------------------+----------------------");
        System.out.printf("%-20s | %20d ns\n", "Bubble Sort", bubbleTime);
        System.out.printf("%-20s | %20d ns\n", "Merge Sort", mergeTime);
        System.out.printf("%-20s | %20d ns\n", "Quick Sort", quickTime);
        System.out.println("---------------------------------------------------------------");
    }
}
