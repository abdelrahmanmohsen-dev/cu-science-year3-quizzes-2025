package SortingAlgorithms;

/**
 * Provides a static, generic implementation of the Selection Sort algorithm.
 * <p>
 * This class sorts an array by repeatedly finding the minimum element
 * from the unsorted part and putting it at the beginning.
 * <p>
 * Time Complexity: O(n^2) in all cases (best, average, worst).
 * Space Complexity: O(1) (in-place).
 */
public class SelectionSort {

    /**
     * Sorts an array in ascending order using the Selection Sort algorithm.
     * The array is sorted "in-place," meaning the original array is modified.
     *
     * @param <T>   The generic type of the array elements (must implement Comparable).
     * @param array The array to be sorted.
     */
    public static <T extends Comparable<T>> void sort(T[] array) {

        int n = array.length;

        // The outer loop iterates through each position in the array.
        // 'i' marks the boundary between the sorted part (left) and unsorted part (right).
        for (int i = 0; i < n; i++) {

            // 'mn' (minimum) will store the index of the smallest element
            // found in the unsorted part of the array.
            // We assume the first element (at index 'i') is the smallest... for now.
            int mn = i;

            // The inner loop scans the entire unsorted part (from i+1 to the end).
            for (int j = i + 1; j < n; j++) {
                // Compare the current element 'j' with our current minimum 'mn'.
                // compareTo() < 0 means array[j] is smaller than array[mn].
                if (array[j].compareTo(array[mn]) < 0) {
                    // We found a new minimum! Store its index.
                    mn = j;
                }
            }

            // After the inner loop, 'mn' holds the index of the true minimum
            // in the unsorted portion.

            // Optimization: Only swap if the minimum isn't already in its correct spot.
            // (i.e., if the smallest element wasn't the one at array[i] already).
            if (i != mn) {
                // Perform a classic swap
                T temp = array[i];
                array[i] = array[mn];
                array[mn] = temp;
            }
        }
    }
}
