package Tasks.Task2.Group1;

import java.util.Arrays;

/**
 * Problem:
 * - Count the total number of occurrences of a specific integer
 * in a given array.
 * <p>
 * Time Complexity:
 * O(N) - where N is the length of the array.
 * Both solutions must visit every element in the array to guarantee
 * a correct count.
 */
public class group1 {

    /**
     * Method 1 (Linear Recursion): Checks the element at the 'start' index
     * and adds the result to the recursive call for the rest of the array.
     * <p>
     * It moves through the array one element at a time (from start to end).
     *
     * @param array The array to search in.
     * @param num   The number to count.
     * @param start The current index to check.
     * @return The count of 'num' from 'start' to the end of the array.
     */
    public static int CountNum(int[] array, int num, int start) {
        // Base case: If we have reached the end of the array, stop.
        if (array.length == start) return 0;

        int count = 0;

        // Check the current element
        if (array[start] == num) count++;

        // Add the result (0 or 1) to the count from the rest of the array
        return count + CountNum(array, num, start + 1);
    }

    /**
     * Method 2 (Divide and Conquer): Splits the array into two halves
     * and recursively counts the occurrences in each half.
     * <p>
     * The final count is the sum of the counts from the left and right halves.
     *
     * @param array The array to search in.
     * @param num   The number to count.
     * @param start The starting index of the current subarray.
     * @param end   The ending index of the current subarray.
     * @return The count of 'num' within the specified range [start, end].
     */
    public static int CountNumDAC(int[] array, int num, int start, int end) {
        // Base case: If the subarray has only one element
        if (start == end) {
            return (array[start] == num) ? 1 : 0;
        }

        // Divide: Calculate the middle index
        int mid = start + (end - start) / 2;

        // Conquer: Recurse on the left half
        int leftCount = CountNumDAC(array, num, start, mid);

        // Conquer: Recurse on the right half
        int rightCount = CountNumDAC(array, num, mid + 1, end);

        // Combine: Return the sum of both halves
        return leftCount + rightCount;
    }


    public static void main(String[] args) {
        // TestCase 1 (using DAC)
        int[] arr1 = {1, 2, 3, 3, 4, 3};
        int num1 = 3;
        System.out.println("DAC Count of : " + num1);
        System.out.println("in " + Arrays.toString(arr1));
        System.out.println("is: " + CountNumDAC(arr1, num1, 0, arr1.length - 1));
        // Expected: 3

        System.out.println("================================");

        // TestCase 2 (using Linear Recursion)
        int[] arr2 = {5, 8, 5, 1, 5, 9, 5};
        int num2 = 5;
        System.out.println("Linear Count of : " + num2);
        System.out.println("in " + Arrays.toString(arr2));
        System.out.println("is: " + CountNum(arr2, num2, 0));
        // Expected: 4

        System.out.println("================================");

        // TestCase 3 (Number not present)
        int[] arr3 = {10, 20, 30, 40};
        int num3 = 7;
        System.out.println("DAC Count of : " + num3);
        System.out.println("in " + Arrays.toString(arr3));
        System.out.println("is: " + CountNumDAC(arr3, num3, 0, arr3.length - 1));
        // Expected: 0
    }
}