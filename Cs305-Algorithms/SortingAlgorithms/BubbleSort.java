package SortingAlgorithms;

/**
 * Provides a static, generic implementation of the Bubble Sort algorithm.
 * This class can sort any array of objects that implement the Comparable interface.
 */
public class BubbleSort {

    /**
     * Sorts an array in ascending order using the Bubble Sort algorithm.
     *
     * @param <T>   This is a generic type parameter. It must be a type that
     * implements the Comparable interface (e.g., Integer, String, Double,
     * or any custom class that implements Comparable).
     * @param arr The array to be sorted.
     */
    public static <T extends Comparable<T>> void sort(T[] arr) {
        if (arr == null || arr.length <= 1)
            return; // No need to sort if the array is null or has 0/1 elements

        int n = arr.length;
        boolean swaped; // Optimization: Stop early if the array is already sorted

        // Outer loop iterates from the end towards the beginning
        // After each pass, the largest element "bubbles up" to its correct position
        for (int i = 0; i < n - 1; i++) {
            swaped = false;

            // Inner loop performs the comparisons and swaps
            for (int j = 0; j < n - i - 1; j++) {

                // Use the compareTo method from the Comparable interface
                // array[j].compareTo(array[j + 1]) > 0 means array[j] is "greater than" array[j + 1]
                if (arr[j].compareTo(arr[j + 1]) > 0) {

                    // Swap elements
                    T temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    swaped = true; // Mark that a swap occurred in this pass
                }
            }

            // If no elements were swapped in the inner loop, the array is sorted
            if (!swaped)
                break;
        }
    }
}