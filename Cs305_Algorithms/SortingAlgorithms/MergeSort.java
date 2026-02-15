package SortingAlgorithms;
/**
 * Provides a static, generic implementation of the Merge Sort algorithm.
 * This class sorts any array of objects that implement the {@link Comparable}
 * interface.
 * <p>
 * This implementation is space-optimized. Unlike naive approaches that
 * create new sub-arrays at each recursive step (which results in
 * {@code O(n log n)} space complexity), this version pre-allocates a
 * single {@code O(n)} helper array.
 * <p>
 * The recursive sorting methods then pass indices (low, high) to define
 * the sub-array ranges, and this single helper array is reused for all
 * merge operations. This reduces the total auxiliary space complexity
 * to a more efficient {@code O(n)}.
 *
 * @author  Mohamed Walid
 */
public class MergeSort {
    /**
     * Public "entry" method to sort the array.
     *
     * @param <T>   Generic type parameter (must implement Comparable)
     * @param array The array to be sorted
     */
    public static <T extends Comparable<T>> void sort(T[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        // Create a helper array one time and pass it down.
        // We use T[] and must cast from Object[]. This is a common
        // pattern with generic array creation in Java.
        @SuppressWarnings("unchecked") // This annotation tells the compiler to skip the unchecked cast of helper array
        T[] helper = (T[]) new Comparable[array.length];

        // Call the recursive helper method
        mergeSort(array, helper, 0, array.length - 1);
    }

    /**
     * The recursive divide-and-conquer method.
     *
     * @param array  The main array being sorted
     * @param helper A temporary array used for merging
     * @param low    The starting index of the sub-array
     * @param high   The ending index of the sub-array
     */
    private static <T extends Comparable<T>> void mergeSort(T[] array, T[] helper, int low, int high) {
        // Base case: if low == high, the sub-array has 1 element and is sorted
        if (low < high) {
            int mid = low + (high - low) / 2; // Avoid potential overflow

            // 1. Divide: Sort the left and right halves recursively
            mergeSort(array, helper, low, mid);
            mergeSort(array, helper, mid + 1, high);

            // 2. Conquer: Merge the two sorted halves
            merge(array, helper, low, mid, high);
        }
    }

    /**
     * Merges two sorted sub-arrays: array[low...mid] and array[mid+1...high]
     *
     * @param array  The main array
     * @param helper The temporary array
     * @param low    Start index of the first sub-array
     * @param mid    End index of the first sub-array
     * @param high   End index of the second sub-array
     */
    private static <T extends Comparable<T>> void merge(T[] array, T[] helper, int low, int mid, int high) {

        // --- Step 1: Copy both halves into the helper array ---
        System.arraycopy(array, low, helper, low, (high - low) + 1);

        // --- Step 2: Merge from helper back into the main array ---

        int i = low;       // Pointer for the left half (in helper)
        int j = mid + 1;   // Pointer for the right half (in helper)
        int k = low;       // Pointer for the main array (where we write to)

        // Compare elements from the left and right halves and copy the smaller
        // one back into the original array
        while (i <= mid && j <= high) {
            if (helper[i].compareTo(helper[j]) <= 0) {
                array[k] = helper[i];
                i++;
            } else {
                array[k] = helper[j];
                j++;
            }
            k++;
        }

        // --- Step 3: Copy any remaining elements from the left half ---
        // (The right half doesn't need this, as its remaining elements
        // would already be in the correct place in the original array)
        while (i <= mid) {
            array[k] = helper[i];
            i++;
            k++;
        }
    }
}