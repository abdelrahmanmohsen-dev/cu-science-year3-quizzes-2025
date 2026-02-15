package SortingAlgorithms;
import java.util.Random;

/**
 * Provides a static, generic implementation of the QuickSort algorithm.
 * <p>
 * This implementation uses a randomized pivot to help avoid the
 * worst-case time complexity of O(n^2).
 * <p>
 * Average-case time complexity: O(n log n).
 * Worst-case time complexity: O(n^2) (though rare with a random pivot).
 * Space complexity: O(log n) (due to the recursion call stack).
 *
 * @author Mohamed Walid
 */
public class QuickSort {

    // A single Random instance for pivot selection
    private static final Random rand = new Random();

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
        quickSort(array, 0, array.length - 1);
    }

    /**
     * The recursive divide-and-conquer helper method.
     *
     * @param array The array being sorted
     * @param low   The starting index of the segment to sort
     * @param high  The ending index of the segment to sort
     */
    private static <T extends Comparable<T>> void quickSort(T[] array, int low, int high) {
        // Base case: If the segment has 0 or 1 elements, it's already sorted.
        if (low < high) {
            // Find the pivot index. Elements to the left are smaller,
            // elements to the right are larger.
            int pivotIndex = partition(array, low, high);

            // Recursively sort the two sub-arrays
            quickSort(array, low, pivotIndex - 1);  // Sort left of pivot
            quickSort(array, pivotIndex + 1, high); // Sort right of pivot
        }
    }

    /**
     * Partitions the array segment array[low...high] around a pivot.
     * This implementation uses the Lomuto partition scheme with a
     * randomized pivot.
     *
     * @return The final index where the pivot element is placed.
     */
    private static <T extends Comparable<T>> int partition(T[] array, int low, int high) {

        // --- 1. Choose a random pivot ---
        // This is a crucial optimization to avoid the worst-case O(n^2)
        // for already-sorted or reverse-sorted arrays.
        int pivotIndex = rand.nextInt(high - low + 1) + low;
        T pivotValue = array[pivotIndex];

        // --- 2. Move pivot to the end ---
        // We temporarily move the pivot to the end of the segment
        // so we can use the simple Lomuto partition logic.
        swap(array, pivotIndex, high);

        // --- 3. Lomuto Partitioning ---
        // 'i' will track the boundary between elements < pivot and
        // elements >= pivot.
        int i = low - 1;

        // Iterate from 'low' up to (but not including) the 'high'
        // index where our pivot is currently sitting.
        for (int j = low; j < high; j++) {
            // If the current element is less than or equal to the pivot
            if (array[j].compareTo(pivotValue) <= 0) {
                // Move the boundary
                i++;
                // Swap the current element into the "smaller" partition
                swap(array, i, j);
            }
        }

        // --- 4. Place pivot in its final sorted position ---
        // The final spot for the pivot is just after the "smaller"
        // partition (at index i + 1).
        swap(array, i + 1, high);

        // Return the pivot's new index
        return i + 1;
    }

    /**
     * Helper method to swap two elements in an array.
     */
    private static <T extends Comparable<T>> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}