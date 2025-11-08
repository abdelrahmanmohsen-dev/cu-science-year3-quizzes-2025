import java.util.Arrays;

/**
 * Implements the Ternary Merge Sort algorithm.
 * <p>
 * This is a "Divide and Conquer" algorithm that works similarly to the
 * standard 2-way Merge Sort. However, instead of splitting the array
 * into two parts, it splits it into three (roughly) equal parts.
 * <p>
 * 1. Divide: The array is split into [low...third], [third+1...mid], [mid+1...high].
 * 2. Conquer: The algorithm recursively sorts all three sub-arrays.
 * 3. Combine: The three sorted sub-arrays are merged back into one
 * sorted array.
 * <p>
 * Time Complexity: O(N log N)
 * - The recurrence relation is T(N) = 3T(N/3) + O(N).
 * - By the Master Theorem (a=3, b=3, d=1), log_b(a) == d.
 * - This falls into the O(N^d log N) case, resulting in O(N log N).
 * - This is asymptotically the same as 2-way Merge Sort, though it
 * may have different constant factors.
 * <p>
 * Space Complexity: O(N)
 * - O(N) is required for the temporary arrays during the merge step.
 * - O(log_3 N) is required for the recursion call stack.
 * - The dominant factor is O(N).
 */
public class TernaryMerge {

    /**
     * Public wrapper method to start the Ternary Merge Sort.
     * This provides a simple API for the user to call.
     *
     * @param arr The integer array to be sorted.
     */
    public static void ternaryMergeSort(int[] arr) {
        // Calls the helper method with the full array bounds
        helperTernaryMergeSort(arr, 0, arr.length - 1);
    }

    /**
     * The main recursive "Divide and Conquer" method.
     * It splits the array into three parts, sorts them, and merges them.
     *
     * @param arr  The array being sorted.
     * @param low  The starting index of the subarray.
     * @param high The ending index of the subarray.
     */
    private static void helperTernaryMergeSort(int[] arr, int low, int high) {
        // Base case: If the subarray has 1 or 0 elements, it is already sorted.
        if (low < high) {
            // Divide: Calculate the two split points (at 1/3 and 2/3)
            int third = low + (high - low) / 3;
            int mid = low + 2 * (high - low) / 3;

            // Conquer: Recursively sort the three parts
            helperTernaryMergeSort(arr, low, third);
            helperTernaryMergeSort(arr, third + 1, mid);
            helperTernaryMergeSort(arr, mid + 1, high);

            // Combine: Merge the three sorted parts
            ternaryMerge(arr, low, third, mid, high);
        }
    }

    /**
     * The "Combine" step. Merges three sorted subarrays into one.
     * Subarrays are:
     * 1. [low...third]
     * 2. [third+1...mid]
     * 3. [mid+1...high]
     * <p>
     * This is done in two passes:
     * 1. Merge part 1 and part 2 into a temporary array ('temp').
     * 2. Merge 'temp' and part 3 into a final array ('merged').
     * 3. Copy 'merged' back into the original array.
     *
     * @param arr    The main array.
     * @param low    Start index of part 1.
     * @param third  End index of part 1.
     * @param mid    End index of part 2.
     * @param high   End index of part 3.
     */
    private static void ternaryMerge(int[] arr, int low, int third, int mid, int high) {

        // --- Step 1: Merge the first two parts ([low...third] and [third+1...mid]) ---
        int[] temp = new int[mid - low + 1]; // Temp array for parts 1 & 2
        int i = low, j = third + 1, k = 0;

        // Standard 2-way merge for parts 1 and 2 into 'temp'
        while (i <= third && j <= mid) {
            temp[k++] = arr[i] < arr[j] ? arr[i++] : arr[j++];
        }
        // Copy any remaining elements from part 1
        while (i <= third) {
            temp[k++] = arr[i++];
        }
        // Copy any remaining elements from part 2
        while (j <= mid) {
            temp[k++] = arr[j++];
        }

        // --- Step 2: Merge the 'temp' array (parts 1+2) with the third part ([mid+1...high]) ---
        int[] merged = new int[high - low + 1]; // Final array for all 3 parts
        i = 0;         // Reset pointer for 'temp' array (which is sorted)
        j = mid + 1;   // Pointer for part 3
        k = 0;         // Reset pointer for final 'merged' array

        // Standard 2-way merge for (temp) and (part 3) into 'merged'
        while (i < temp.length && j <= high) {
            merged[k++] = temp[i] < arr[j] ? temp[i++] : arr[j++];
        }
        // Copy any remaining elements from 'temp'
        while (i < temp.length) {
            merged[k++] = temp[i++];
        }
        // Copy any remaining elements from part 3
        while (j <= high) {
            merged[k++] = arr[j++];
        }

        // --- Step 3: Copy the final 'merged' array back into the original 'arr' ---
        // The sorted elements from 'merged' are placed back into 'arr'
        // starting at the 'low' index.
        System.arraycopy(merged, 0, arr, low, merged.length);
    }

    public static void main(String[] args) {
        // Test case
        int[] arr = {10, 54, 20, 4, 8, 10, 15, -5, 100, 3};
        System.out.println("Original array:" + Arrays.toString(arr));

        // Run the sort
        ternaryMergeSort(arr);

        System.out.println("Sorted array:" + Arrays.toString(arr));
        // Expected: [-5, 3, 4, 8, 10, 10, 15, 20, 54, 100]
    }
}