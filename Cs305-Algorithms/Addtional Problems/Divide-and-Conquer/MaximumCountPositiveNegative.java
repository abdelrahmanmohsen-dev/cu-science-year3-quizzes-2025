/**
 * Problem: Maximum Count of Positive and Negative Integers
 * --------------------------------------------------------
 * Given a sorted array of integers nums (can include negative, zero, positive),
 * return the maximum between:
 * 1. the count of positive integers
 * 2. the count of negative integers
 *
 * Note:
 * - Zero is neither positive nor negative.
 *
 * Example:
 * ---------
 * Input: nums = [-3,-2,-1,0,1,2,3]
 * Output: 3
 * Explanation:
 * Positive integers = [1,2,3] → count = 3
 * Negative integers = [-3,-2,-1] → count = 3
 * Maximum count = 3
 *
 * Constraints:
 * ------------
 * - 1 <= nums.length <= 2000
 * - -2000 <= nums[i] <= 2000
 * - nums is sorted in non-decreasing order
 *
 * This class uses:
 * - Binary search to find first positive and first negative efficiently
 */
public class MaximumCountPositiveNegative {

    /**
     * Returns the maximum count between positive and negative integers in a sorted array.
     *
     * @param nums input sorted array
     * @return the maximum count of either positive or negative integers
     */
    public static int maximumCount(int[] nums) {
        if (nums == null || nums.length == 0)
            throw new IllegalArgumentException("nums must not be null or empty");

        // ===================== Find number of positive integers =====================
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            // If nums[mid] > 0, search left to find the first positive number
            if (nums[mid] > 0) high = mid - 1;
            else low = mid + 1; // else move right
        }
        int posCount = nums.length - low; // Count of positive numbers

        // ===================== Find number of negative integers =====================
        low = 0;
        high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            // If nums[mid] < 0, search right to find last negative number
            if (nums[mid] < 0) low = mid + 1;
            else high = mid - 1; // else move left
        }
        int negCount = low; // Count of negative numbers

        // Return the maximum count
        return Math.max(posCount, negCount);
    }

    // ======================== SIMPLE TESTS ========================
    public static void main(String[] args) {
        System.out.println("=== Tests for MaximumCountPositiveNegative ===");

        // Test 1
        {
            int[] arr = {-3, -2, -1, 0, 1, 2, 3};
            int result = maximumCount(arr);
            System.out.println("Test 1: nums = [-3,-2,-1,0,1,2,3] → Result = " + result + " | Expected = 3");
        }

        // Test 2 (all positives)
        {
            int[] arr = {1, 2, 3, 4};
            int result = maximumCount(arr);
            System.out.println("Test 2: nums = [1,2,3,4] → Result = " + result + " | Expected = 4");
        }

        // Test 3 (all negatives)
        {
            int[] arr = {-5, -4, -3};
            int result = maximumCount(arr);
            System.out.println("Test 3: nums = [-5,-4,-3] → Result = " + result + " | Expected = 3");
        }

        // Test 4 (mixed with zeros)
        {
            int[] arr = {-2, -1, 0, 0, 1};
            int result = maximumCount(arr);
            System.out.println("Test 4: nums = [-2,-1,0,0,1] → Result = " + result + " | Expected = 2");
        }

        // Test 5 (single zero)
        {
            int[] arr = {0};
            int result = maximumCount(arr);
            System.out.println("Test 5: nums = [0] → Result = " + result + " | Expected = 0");
        }
    }
}