/*
Problem: Majority Element
---------------------------------
Link: https://leetcode.com/problems/majority-element/

Description:
Given an array nums of size n, return the majority element.
The majority element is the element that appears more than ⌊n / 2⌋ times.
You may assume that the majority element always exists in the array.

Example:
Input: nums = [3,2,3]
Output: 3

Input: nums = [2,2,1,1,1,2,2]
Output: 2

Approach:
This solution uses the Divide and Conquer technique.
- Divide the array into two halves.
- Recursively find the majority element in each half.
- Combine results by counting occurrences of each candidate in the current range.
*/

public class MajorityElement {

    // Main function to test the algorithm
    public static void main(String[] args) {
        // Test Case 1
        int[] nums1 = {3, 2, 3};
        System.out.println("Test 1: " + majorityElement(nums1)); // Expected: 3

        // Test Case 2
        int[] nums2 = {2, 2, 1, 1, 1, 2, 2};
        System.out.println("Test 2: " + majorityElement(nums2)); // Expected: 2

        // Test Case 3
        int[] nums3 = {1, 1, 1, 2, 3};
        System.out.println("Test 3: " + majorityElement(nums3)); // Expected: 1

        // Test Case 4
        int[] nums4 = {6, 5, 5};
        System.out.println("Test 4: " + majorityElement(nums4)); // Expected: 5
    }

    // Wrapper function
    public static int majorityElement(int[] nums) {
        return majorityElement(0, nums.length - 1, nums);
    }

    // Recursive function using divide and conquer
    public static int majorityElement(int l, int r, int[] arr) {
        if (l == r) {
            return arr[l]; // Base case: only one element
        }

        int mid = (l + r) / 2;

        // Recursively find majority elements in left and right halves
        int left = majorityElement(l, mid, arr);
        int right = majorityElement(mid + 1, r, arr);

        // If both halves agree on the same element, return it
        if (left == right) {
            return left;
        }

        // Otherwise, count occurrences of each candidate
        int countLeft = 0, countRight = 0;
        for (int i = l; i <= r; i++) {
            if (arr[i] == left) countLeft++;
            if (arr[i] == right) countRight++;
        }

        // Return the element that appears more
        return (countLeft > countRight) ? left : right;
    }
}
