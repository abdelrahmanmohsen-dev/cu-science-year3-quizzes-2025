package Semester_2_Spring_2026.Quiz_1;
/**
 * CS305 - Quiz 1 - Spring 2026
 * Write a Java method that takes an int array as a parameter
 * and returns a new array containing only the elements that are greater than 5.
 * Example 1:
 * Input: [1,9,3,8,10,4,5]
 * Output: [9,8,10]
 * Example 2:
 * Input file: [4,9,10,5,2,8,6,9]
 * Output: [9,10,8,6,9]
 */

import java.util.*;

public class Quiz1 {
    /**
     * Returns a new array containing only the elements
     * from the input array that are greater than 5.
     *
     * @param arr the input integer array
     * @return a new array containing all values greater than 5
     */
    public static int[] greaterThanFive(int[] arr) {
        // If the array is null, return an empty array to avoid NullPointerException
        if (arr == null) return new int[]{};
        // Create a dynamic list to store elements > 5 (we chose it due to it's dynamic size)
        ArrayList<Integer> list = new ArrayList<>();
        // Iterate through the input array
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 5)
                list.add(arr[i]);
        }
        // Create result array with the same size as the list
        int[] result = new int[list.size()];
        // Iterate to copy elements from list to array
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i); // Assign each element from list to result array
        }
        // Return the new filtered array
        return result;
    }

    public static void main(String[] args) {
        // Test 1: Normal case
        System.out.println("Test 1 → " +
                Arrays.toString(greaterThanFive(new int[]{1, 9, 3, 8, 10, 4, 5})));
        // Expected: [9, 8, 10]


        // Test 2: Another normal case
        System.out.println("Test 2 → " +
                Arrays.toString(greaterThanFive(new int[]{4, 9, 10, 5, 2, 8, 6, 9})));
        // Expected: [9, 10, 8, 6, 9]


        // Test 3: All elements ≤ 5
        System.out.println("Test 3 → " +
                Arrays.toString(greaterThanFive(new int[]{1, 2, 3, 4, 5})));
        // Expected: []


        // Test 4: All elements > 5
        System.out.println("Test 4 → " +
                Arrays.toString(greaterThanFive(new int[]{6, 7, 8, 9})));
        // Expected: [6, 7, 8, 9]


        // Test 5: Empty array
        System.out.println("Test 5 → " +
                Arrays.toString(greaterThanFive(new int[]{})));
        // Expected: []


        // Test 6: Negative numbers
        System.out.println("Test 6 → " +
                Arrays.toString(greaterThanFive(new int[]{-1, -5, 6, 7})));
        // Expected: [6, 7]


        // Test 7: Mixed positive and negative
        System.out.println("Test 7 → " +
                Arrays.toString(greaterThanFive(new int[]{-10, 0, 5, 6, 100})));
        // Expected: [6, 100]

    
        // Test 8: Duplicates
        System.out.println("Test 8 → " +
                Arrays.toString(greaterThanFive(new int[]{6, 6, 6, 5, 5})));
        // Expected: [6, 6, 6]

        // Test 9: Null array
        System.out.println("Test 9 → " +
                Arrays.toString(greaterThanFive(null)));
        // Expected: Exception (NullPointerException)
    }
}
