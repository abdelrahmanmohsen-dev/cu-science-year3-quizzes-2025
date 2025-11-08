/**
 * Problem: Find Peak Element
 * ---------------------------
 * A peak element is an element that is strictly greater than its neighbors.
 *
 * Given an integer array nums, find a peak element and return its index.
 * If the array contains multiple peaks, return the index of any one of them.
 *
 * You may imagine that nums[-1] = nums[n] = -∞.
 * That means the first and last elements have only one real neighbor.
 *
 * Example for the virtual boundaries:
 * -----------------------------------
 * nums = [1, 2, 3, 1]
 * We can imagine it as: [-∞, 1, 2, 3, 1, -∞]
 * Here, 3 is greater than both its neighbors (2 and 1), so index = 2 is a peak.
 *
 * Example:
 * ---------
 * Input: nums = [1, 2, 3, 1]
 * Output: 2
 * Explanation: 3 is a peak element, and your function should return index = 2.
 *
 * Constraints:
 * ------------
 * - 1 <= nums.length <= 1000
 * - -2^31 <= nums[i] <= 2^31 - 1
 * - nums[i] != nums[i + 1] for all valid i
 *
 * This class includes:
 * - Recursive Binary Search approach (Divide and Conquer)
 */

public class FindPeakElement {

    /**
     * Finds a peak element in the given array and returns its index.
     *
     * @param nums the array of integers
     * @return the index of a peak element
     * @throws IllegalArgumentException if nums is null or empty
     */
    public static int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0)
            throw new IllegalArgumentException("Array must not be null or empty");
        return customBinarySearch(nums, 0, nums.length - 1);
    }

    /**
     * Recursive helper that uses Binary Search logic to find the peak.
     *
     * @param nums the array of integers
     * @param low  the lower bound index of the current search space
     * @param high the upper bound index of the current search space
     * @return     the index of a peak element
     */
    private static int customBinarySearch(int[] nums, int low, int high) {
        // Base Case 1: Only one element left
        if (low == high)
            return low;

        // Base Case 2: Two elements left — pick the greater one
        if (high == low + 1)
            return (nums[low] >= nums[high]) ? low : high;

        int mid = (low + high) / 2;

        // Case 1: mid is a peak
        if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1])
            return mid;

        // Case 2: peak lies on the right side
        if (nums[mid] < nums[mid + 1])
            return customBinarySearch(nums, mid + 1, high);

        // Case 3: peak lies on the left side
        return customBinarySearch(nums, low, mid - 1);
    }

    // ======================== TESTS ========================
    public static void main(String[] args) {
        System.out.println("=== Tests for Find Peak Element ===");

        // Test 1
        {
            int[] arr = {1, 2, 3, 1};
            int result = findPeakElement(arr);
            System.out.println("Test 1: [1,2,3,1] → Peak Index = " + result + " | Expected = 2");
        }

        // Test 2
        {
            int[] arr = {1, 2, 1, 3, 5, 6, 4};
            int result = findPeakElement(arr);
            // Possible peaks are at index 1 (2) or index 5 (6)
            System.out.println("Test 2: [1,2,1,3,5,6,4] → Peak Index = " + result + " | Expected = 1 or 5");
        }

        // Test 3
        {
            int[] arr = {10, 9, 8, 7, 6};
            int result = findPeakElement(arr);
            // Peak at index 0 since array is strictly decreasing
            System.out.println("Test 3: [10,9,8,7,6] → Peak Index = " + result + " | Expected = 0");
        }

        // Test 4
        {
            int[] arr = {1, 3, 5, 7, 9};
            int result = findPeakElement(arr);
            // Peak at the last index since array is strictly increasing
            System.out.println("Test 4: [1,3,5,7,9] → Peak Index = " + result + " | Expected = 4");
        }
    }
}