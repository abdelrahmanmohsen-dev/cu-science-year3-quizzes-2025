package SortingAlgorithms;

/**
 * Provides a static, generic implementation of the Insertion Sort algorithm.
 * This class can sort any array of objects that implement the Comparable interface.
 */
public class InsertionSort {

    /**
     * Sorts an array in ascending order using the Insertion Sort algorithm.
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

        // Outer loop iterates from the second element (index 1) to the end
        for (int i = 1; i < n; i++) {
            // Pick the element to be inserted into the sorted portion
            T key = arr[i];

            // Start from the element just before the key
            int j = i - 1;

            // Move elements of the sorted portion (array[0...i-1])
            // that are greater than the key, one position to their right.
            // This makes space for the key to be inserted.
            while (j >= 0 && arr[j].compareTo(key) > 0) {
                arr[j + 1] = arr[j];
                j -= 1;
            }

            // Insert the key into its correct sorted position
            arr[j + 1] = key;
        }
    }
}
