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
        System.out.println("\nSorted Output (Quick Sort):");
        System.out.println(Arrays.toString(quickArray));

        System.out.println("\nAlgorithm Performance Comparison");
        System.out.println("-----------------------------------------");
        System.out.println("Bubble Sort : " + bubbleTime + " ns");
        System.out.println("Merge Sort  : " + mergeTime + " ns");
        System.out.println("Quick Sort  : " + quickTime + " ns");
    }
}
