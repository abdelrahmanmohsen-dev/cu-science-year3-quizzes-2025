package task_1.group_3;

import java.util.Arrays;

public class Group_3 {
    /*
    * Problem:
    *       -Write an Algorithm that moves all zeros in an integer array to the end, while keeping the order of the other elements the same.
    *        You must do it (in-place) and in linear time O(n)-
    *
    * Solving Method:
    *       1) Starting with a pointer on the start of the array that will mark the position of the next Integer.
    *       2) Loop over the array and if we encounter a non-zero Integer we swap it with the zero that our pointer at.
    *
    * Time Complexity:
    *       O(N) since we are just loop over the array once.
    * */
    public static void moveZeroes(int[] nums) {
        // Initialize the pointer
        int place = 0;

        // Loop over the array and swap any non-zero Integer with the pointed zero
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                // Check that the number we are at is not the same that the pointer's
                if (i != place) {
                    nums[place] = nums[i];
                    nums[i] = 0;

                }
                // moving the pointer one step after placing the integer on the right spot
                place++;
            }
        }
    }

    // TestCases
    public static void main(String[] args) {
        // Test1
        int[] arr1 = {1, 0, 2, 0, 3, 0, 4};
        System.out.println("Array before moveZeroes: " + Arrays.toString(arr1));
        moveZeroes(arr1);
        System.out.println("Array after moveZeroes: " + Arrays.toString(arr1));
        // Expected: [1, 2, 3, 4, 0, 0, 0]

        System.out.println();
        System.out.println("==================================");
        System.out.println();

        // Test2
        int[] arr2 = {0, 0, 5, 2, 10, 4};
        System.out.println("Array before moveZeroes: " + Arrays.toString(arr2));
        moveZeroes(arr2);
        System.out.println("Array after moveZeroes: " + Arrays.toString(arr2));
        // Expected: [5, 2, 10, 4, 0, 0]

        System.out.println();
        System.out.println("================================");
        System.out.println();

        // Test3
        int[] arr3 = {1, 22, 0, 51, 4, 5, 0};
        System.out.println("Array before moveZeroes: " + Arrays.toString(arr3));
        moveZeroes(arr3);
        System.out.println("Array after moveZeroes: " + Arrays.toString(arr3));
        // Expected: [1, 22, 51, 4, 5, 0, 0]
    }
}
