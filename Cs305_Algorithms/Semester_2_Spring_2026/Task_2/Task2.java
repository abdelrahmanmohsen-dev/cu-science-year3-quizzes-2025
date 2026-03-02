package Semester_2_Spring_2026.Task_2;

/**
 * CS305 - Task 2 - Spring 2026
 * Write a Java method that takes an int array as a parameter
 * and returns the sum of its elements using recursion with two pointers
 * Example 1:
 * Input: [1,9,3,8,10,4]
 * Output: 35
 * Example 2:
 * Input file: [1,2,3,4,5]
 * Output: 15
 */

public class Task2 {
    /**
     * Returns the sum of array elements using recursion with two pointers.
     *
     * @param arr   the input array
     * @param left  starting index
     * @param right ending index
     * @return sum of elements between left and right
     */
    public static int addArrayElements(int[] arr, int left, int right) {
        // If the array is null, return 0 to avoid NullPointerException
        if (arr == null) return 0;
        // If left exceeds right, no elements remain to sum
        if (left > right) return 0;
        // If both pointers meet, return the single remaining element
        if (left == right) return arr[left];
        // Add the left element and the right elements & update recursively for the inner subarray
        return arr[left] + arr[right] + addArrayElements(arr, left + 1, right - 1);
    }

    public static void main(String[] args) {
        // Test 1: Normal odd-length array
        System.out.println("Test 1 → " +
                addArrayElements(new int[]{1, 2, 3, 4, 5}, 0, 4));
        // Expected: 15


        // Test 2: Normal even-length array
        System.out.println("Test 2 → " +
                addArrayElements(new int[]{1, 9, 3, 8, 10, 4}, 0, 5));
        // Expected: 35


        // Test 3: Single element
        System.out.println("Test 3 → " +
                addArrayElements(new int[]{7}, 0, 0));
        // Expected: 7


        // Test 4: Empty array
        System.out.println("Test 4 → " +
                addArrayElements(new int[]{}, 0, -1));
        // Expected: 0


        // Test 5: Null array
        System.out.println("Test 5 → " +
                addArrayElements(null, 0, -1));
        // Expected: 0


        // Test 6: Array with negative numbers
        System.out.println("Test 6 → " +
                addArrayElements(new int[]{-1, -2, -3, -4}, 0, 3));
        // Expected: -10


        // Test 7: Left greater than right (invalid range)
        System.out.println("Test 7 → " +
                addArrayElements(new int[]{1, 2, 3, 4}, 3, 1));
        // Expected: 0


        // Test 8: Partial range (not full array)
        System.out.println("Test 8 → " +
                addArrayElements(new int[]{1, 2, 3, 4, 5, 6}, 1, 4));
        // Expected: 14  (2 + 3 + 4 + 5)
    }
}
