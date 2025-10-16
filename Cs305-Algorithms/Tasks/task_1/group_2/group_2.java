package task_1.group_2;


import java.util.Arrays;

/*
* Problem:
*           -Merge two sorted arrays into a sorted one using O(n) algorithm-
*
* Solving Method:
*           1) We will use two pointers one on the start of each array and another pointer on the solution array
*           2) Iterating throw each array and adding the smallest integer to our solution array
*           3) If one of the arrays is smaller than the other we add the rest of integers of the second one directly
*
* Time Complexity:
*           -Since we will loop over the integers of the two arrays so it will be O(n + m)
*               Where: n = size of array1, m = size of array2
*           -For simplicity will be O(N)
* */
public class group_2 {

    public static int[] merge(int[] arr1 , int [] arr2) {

        // Solution Array
        int[] result = new int[arr1.length + arr2.length];

        // Pointer for every array
        int i = 0, j = 0, k = 0;

        // loop over the two arrays and adding the smallest element
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j]) result[k] = arr1[i++];
            else result[k] = arr2[j++];
            k++;
        }

        // adding the integers of the other array directly
        while (i < arr1.length) result[k++] = arr1[i++];
        while (j < arr2.length) result[k++] = arr2[j++];
        return result;
    }

    // Alternative Method(Java internal method based && ternary conditional operator):
    public static int[] merge2(int[] arr1, int[] arr2){
        int[] merged = new int[arr1.length + arr2.length];

        int i = 0 , j = 0 , k = 0;

        while(i < arr1.length && j < arr2.length) merged[k++] = arr1[i] < arr2[j] ? arr1[i++] : arr2[j++];

        if (i < arr1.length) System.arraycopy(arr1 , i , merged , k , arr1.length - i);
        if (j < arr2.length) System.arraycopy(arr2 , j , merged , k , arr2.length - j);
        return merged;
    }

    // TestCases
    public static void main(String[] args) {
        // Test1
        int[] test1_arr1 = {1, 2, 3, 4},  test1_arr2 = {5, 6, 7, 8};
        System.out.println("test 1:\nArray1: " + Arrays.toString(test1_arr1) + ", Array2: "  + Arrays.toString(test1_arr2));
        System.out.println("Merged Array: " + Arrays.toString(merge(test1_arr1 , test1_arr2)));
        // Expected: [1, 2, 3, 4, 5, 6, 7, 8]

        System.out.println("================================");

        // Test2
        int[] test2_arr1 = {3, 5, 7, 9},   test2_arr2 = {6, 8, 10, 12, 13};
        System.out.println("test 2:\nArray1: " + Arrays.toString(test2_arr1) + ", Array2: " + Arrays.toString(test2_arr2));
        System.out.println("Merged Array: " + Arrays.toString(merge2(test2_arr1, test2_arr2)));
        // Expected: [3, 5, 6, 7, 8, 9, 10, 12, 13]

        System.out.println("================================");

        // Test3
        int[] test3_arr1 = {1, 2, 3, 4},  test3_arr2 = {1, 2, 3, 4};
        System.out.println("test 3:\nArray1: " + Arrays.toString(test3_arr1) + ", Array2: " + Arrays.toString(test3_arr2));
        System.out.println("Merged Array: " + Arrays.toString(merge(test3_arr1, test3_arr2)));
        // Expected: [1, 1, 2, 2, 3, 3, 4, 4]
    }
}
