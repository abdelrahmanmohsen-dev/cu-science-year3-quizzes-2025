package Tasks.Task2.Group3;

import java.util.Arrays;

/**
 * Problem:
 * - Check if a given array of integers is sorted in ascending order.
 * <p>
 * Time Complexity:
 * O(N) - where N is the length of the array.
 * Both solutions must visit every element in the worst case to
 * confirm that the entire array is sorted.
 */
public class group3 {

    /**
     * Method 1 (Linear Recursion): Checks if the current element
     * (at 'idx') is less than or equal to the next element.
     * <p>
     * If it is, it recurses to check the next pair (idx + 1).
     * If it's not, it immediately returns false.
     *
     * @param arr The array to check.
     * @param idx The current index to check (starts at 0).
     * @return true if the array is sorted, false otherwise.
     */
    public static boolean isSorted(int[] arr, int idx) {
        // Base case: If we're at the last element, it's sorted.
        if (idx >= arr.length - 1) return true;

        // Check the current pair
        if (arr[idx] > arr[idx + 1]) {
            return false;
        } else {
            // Recurse on the rest of the array
            return isSorted(arr, idx + 1);
        }
    }

    /**
     * Method 2 (Divide and Conquer): Splits the array into two halves
     * and checks three things:
     * 1. Is the left half sorted (recursive call)?
     * 2. Is the right half sorted (recursive call)?
     * 3. Is the "seam" (the last element of the left half and the
     * first of the right half) sorted?
     *
     * @param arr The array to check.
     * @param l   The left bound (start) of the subarray.
     * @param r   The right bound (end) of the subarray.
     * @return true if the subarray [l, r] is sorted, false otherwise.
     */
    public static boolean isSortedDAC(int[] arr, int l, int r) {
        // Base case: An array of 0 or 1 element is always sorted.
        if (l >= r) return true;

        // Divide: Find the middle
        int mid = l + (r - l) / 2;

        // Check the "seam" between the two halves.
        // This check is crucial and must be done.
        boolean IsTheMidSorted = (arr[mid] <= arr[mid + 1]);

        // Conquer and Combine:
        // Return true ONLY if the seam is sorted AND
        // the left half is sorted AND the right half is sorted.
        return IsTheMidSorted
                && isSortedDAC(arr, l, mid)
                && isSortedDAC(arr, mid + 1, r);
    }

    public static void main(String[] args) {
        // TestCase 1 (DAC, Sorted)
        int[] arr1 = {10, 20, 30, 40, 50};
        System.out.println("DAC Check on : " + Arrays.toString(arr1));
        // We must check if length > 0 before accessing arr.length - 1
        boolean res1 = (arr1.length <= 1) ? true : isSortedDAC(arr1, 0, arr1.length - 1);
        System.out.println("is: " + (res1 ? "Sorted" : "Not Sorted"));
        // Expected: Sorted

        System.out.println("================================");

        // TestCase 2 (Linear, Not Sorted)
        int[] arr2 = {1, 2, 5, 4, 6};
        System.out.println("Linear Check on : " + Arrays.toString(arr2));
        boolean res2 = isSorted(arr2, 0);
        System.out.println("is: " + (res2 ? "Sorted" : "Not Sorted"));
        // Expected: Not Sorted

        System.out.println("================================");

        // TestCase 3 (DAC, Sorted with Duplicates)
        int[] arr3 = {1, 2, 2, 3, 4, 4};
        System.out.println("DAC Check on : " + Arrays.toString(arr3));
        boolean res3 = (arr3.length <= 1) ? true : isSortedDAC(arr3, 0, arr3.length - 1);
        System.out.println("is: " + (res3 ? "Sorted" : "Not Sorted"));
        // Expected: Sorted

        System.out.println("================================");

        // TestCase 4 (Linear, Empty Array)
        int[] arr4 = {};
        System.out.println("Linear Check on : " + Arrays.toString(arr4));
        boolean res4 = isSorted(arr4, 0);
        System.out.println("is: " + (res4 ? "Sorted" : "Not Sorted"));
        // Expected: Sorted
    }
}