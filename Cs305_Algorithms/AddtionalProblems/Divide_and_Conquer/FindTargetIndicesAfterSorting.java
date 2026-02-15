package AddtionalProblems.Divide_and_Conquer;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem: Find Target Indices After Sorting Array
 * ------------------------------------------------
 * You are given a 0-indexed integer array nums and a target value.
 * Return a list of the indices of nums that equal target after sorting nums in non-decreasing order.
 *
 * Example:
 * --------
 * Input: nums = [1,2,5,2,3], target = 2
 * After sorting: [1,2,2,3,5]
 * Output: [1,2]
 *
 * Constraints (according to LeetCode problem):
 * - 1 <= nums.length <= 100
 * - 0 <= nums[i] <= 100
 *
 * This class provides:
 * - A counting sort based solution (suitable because nums[i] is in [0..100])
 * - A recursive helper that finds all indices equal to target (after sorting)
 * - Simple tests with expected outputs
 */
public class FindTargetIndicesAfterSorting {

    /**
     * Returns the list of indices of target after sorting nums in non-decreasing order.
     *
     * @param nums   input array (will be modified by counting sort)
     * @param target target value to find after sorting
     * @return list of indices (0-based) where sorted nums[index] == target
     * @throws IllegalArgumentException if nums is null or empty
     */
    public static List<Integer> targetIndices(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            throw new IllegalArgumentException("nums must not be null or empty");

        // Counting sort (stable, O(n + range)) — sorts ascending
        countingSort(nums);

        // Collect indices where value == target using recursive divide-and-conquer
        return findIndex(nums, target, 0, nums.length - 1);
    }

    /**
     * Recursively find indices of target in nums[low..high].
     * This returns indices in ascending order.
     *
     * @param nums   sorted array
     * @param target target value
     * @param low    lower bound index
     * @param high   upper bound index
     * @return list of indices where nums[index] == target
     */
    private static List<Integer> findIndex(int[] nums, int target, int low, int high) {
        // Step 1: Create a new list to store indices of 'target' found in this range
        List<Integer> list = new ArrayList<>();

        // Step 2: Base case 1 — if the current range is invalid (no elements)
        // e.g., low > high means the recursion has crossed bounds → return empty list.
        if (low > high) return list;

        // Step 3: Base case 2 — if the range has exactly one element
        if (low == high) {
            // If that element equals target → add its index to the list
            if (nums[low] == target) list.add(low);
            // Return list (either empty or containing one index)
            return list;
        }

        // Step 4: Recursive case — divide the current range into two halves
        int mid = (low + high) / 2;

        // Step 5: Recursively search in the left half [low..mid]
        List<Integer> left = findIndex(nums, target, low, mid);

        // Step 6: Recursively search in the right half [mid+1..high]
        List<Integer> right = findIndex(nums, target, mid + 1, high);

        // Step 7: Combine results from left and right halves into one list
        left.addAll(right);

        // Step 8: Return the combined list containing all indices of 'target'
        return left;
    }

    /**
     * Counting sort for values in range [0..100].
     * Modifies the input array in-place to be sorted in non-decreasing order.
     *
     * @param arr array to sort (will be modified)
     */
    public static void countingSort(int[] arr) {
        final int RANGE = 100; // values 0..100
        int[] freq = new int[RANGE + 1];

        for (int item : arr) {
            if (item < 0 || item > RANGE)
                throw new IllegalArgumentException("values must be in range [0..100] for this countingSort");
            freq[item]++;
        }

        int index = 0;
        for (int val = 0; val <= RANGE; val++) {
            while (freq[val] > 0) {
                arr[index++] = val;
                freq[val]--;
            }
        }
    }

    // ======================== SIMPLE TESTS ========================
    public static void main(String[] args) {
        System.out.println("=== Tests for FindTargetIndicesAfterSorting ===");

        // Test 1 (example)
        {
            int[] arr = {1, 2, 5, 2, 3};
            int target = 2;
            int[] copy = arr.clone();
            List<Integer> result = targetIndices(copy, target);
            System.out.println("Test 1: nums = [1,2,5,2,3], target = 2");
            System.out.println("Sorted: [1,2,2,3,5]");
            System.out.println("Result = " + result + " | Expected = [1, 2]");
        }

        // Test 2 (no target)
        {
            int[] arr = {0, 0, 1, 1, 2};
            int target = 3;
            int[] copy = arr.clone();
            List<Integer> result = targetIndices(copy, target);
            System.out.println("\nTest 2: nums = [0,0,1,1,2], target = 3");
            System.out.println("Sorted: [0,0,1,1,2]");
            System.out.println("Result = " + result + " | Expected = []");
        }

        // Test 3 (all equal)
        {
            int[] arr = {4, 4, 4, 4};
            int target = 4;
            int[] copy = arr.clone();
            List<Integer> result = targetIndices(copy, target);
            System.out.println("\nTest 3: nums = [4,4,4,4], target = 4");
            System.out.println("Sorted: [4,4,4,4]");
            System.out.println("Result = " + result + " | Expected = [0, 1, 2, 3]");
        }

        // Test 4 (single element)
        {
            int[] arr = {7};
            int target = 7;
            int[] copy = arr.clone();
            List<Integer> result = targetIndices(copy, target);
            System.out.println("\nTest 4: nums = [7], target = 7");
            System.out.println("Sorted: [7]");
            System.out.println("Result = " + result + " | Expected = [0]");
        }
    }
}
