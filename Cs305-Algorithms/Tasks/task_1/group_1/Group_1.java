package task_1.group_1;

import java.util.Arrays;

/*
* Problem:
*       -Write a Θ(n) algorithm that sorts n distinct integers, ranging in size between
*        1 and kn inclusive, where k is a constant positive integer. (Hint: Use a keyElement array.)-
*
* Solving Method:
*       -Since there is a constraint on the problem that the numbers are between 1 and k*n, we can use a KeyElement array
*        is an array of size (k*n + 1), then looping over the original array and for every element, set its index in the key array to true
*
*       -Then, if I start iterating over the key array, I will find that every element is in the right place
*
* Time Complexity:
*       -Total time complexity O(kn + n) simplifies to O(n + n), which is O(n).
*        Since the algorithm must at least look at every element once (Ω(n)), the final tight bound is Θ(n).
* */

public class Group_1 {
    public static void FastestSort(int[] arr, int k) {
        int n = arr.length;

        // Initializing the keyElement array
        boolean[] keyElementArray = new boolean[k*n + 1];

        // Marking every element in the original array to True
        for (int num : arr) {
            keyElementArray[num] = true;
        }

        int idx = 0;

        // Reassign the original array to its sorted form
        for (int i = 0; i < keyElementArray.length; i++) {
            if (keyElementArray[i])
                arr[idx++] = i;
        }

        /* In another version of the problem, the elements will not be distinct
        *   In this case we instead of a boolean array we can use an integer array
        *   and for every element of the array, add 1 to its index
        *
        *  So, when I start reassigning my original array when iterating over the key array, I add every element its corresponding number of times in the key array
        * */
    }

    public static void main(String[] args) {

        // Test1
        int[] arr = {10, 5, 7, 2, 3, 0};
        System.out.println("Array: " + Arrays.toString(arr) + ", k = 2");
        FastestSort(arr, 2);
        System.out.println("Sorted Array: " + Arrays.toString(arr));
        // Expected: [0, 2, 3, 5, 7, 10]

        System.out.println();
        System.out.println("==================================");
        System.out.println();

        // Test2
        int[] arr2 = {87, 5, 48, 97, 6, 50, 0};
        System.out.println("Array: " + Arrays.toString(arr2) + ", k = 15");
        FastestSort(arr2, 15);
        System.out.println("Sorted Array: " + Arrays.toString(arr2));
        // Expected: [0, 5, 6, 48, 50, 87, 97]

        System.out.println();
        System.out.println("==================================");
        System.out.println();

        // Test3
        int[] arr3 = {6, 8, 7, 2, 9, 1};
        System.out.println("Array: " + Arrays.toString(arr3) + ", k = 3");
        FastestSort(arr3, 3);
        System.out.println("Sorted Array: " + Arrays.toString(arr3));
        // Expected: [1, 2, 6, 7, 8, 9]
    }
}
