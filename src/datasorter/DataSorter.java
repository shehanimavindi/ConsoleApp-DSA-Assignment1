import java.util.Arrays;

public class DataSorter {
    
    // This class is responsible for comparing the sorting algorithms
    // It measures execution time and prints a neat comparison table
    public static void compareSortingAlgorithms(int[] originalArray) {
        
        System.out.println("\nOriginal Dataset:");
        System.out.println(Arrays.toString(originalArray));
        
        // Make copies of the original array for each sort
        int[] bubbleArray = Arrays.copyOf(originalArray, originalArray.length);
        int[] mergeArray = Arrays.copyOf(originalArray, originalArray.length);
        int[] quickArray = Arrays.copyOf(originalArray, originalArray.length);

        // Measure and record the time taken for Bubble Sort
        long start = System.nanoTime();
        SortingAlgorithms.bubbleSort(bubbleArray);
        long bubbleTime = System.nanoTime() - start;

        // Measure and record the time taken for Merge Sort
        start = System.nanoTime();
        SortingAlgorithms.mergeSort(mergeArray, 0, mergeArray.length - 1);
        long mergeTime = System.nanoTime() - start;

        // Measure and record the time taken for Quick Sort
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
