package task_1.group_4;

import java.util.Arrays;

public class group_4 {
    /*
     * Given a sorted array and a number that is not in the array
     * Write an algorithm that returns the index of the new number so the array is still sorted
     * and with time complexity O(log(n))
     *
     * input: arr =  {1, 3, 5}, x = 2
     * output : 1 (the index that the x = 2 should be in)
     *
     * solving method:
     *         since the array is sorted and limited with O(log(n))
     * 		  We will use the binary search and return the left pointer
     *         because it points to the position of the first element that is strictly greater than the given number
     * */
    public static int newLocation(int[] arr, int number) {
        int n = arr.length;
        int l = 0, r = n-1;

        // binary search over the array
        while (l <= r) {
            int mid = (l+r)/2;

            if (arr[mid] > number) {
                r = mid-1;
            }
            else {
                l = mid+1;
            }
        }
        return l;
    }

    // TestCases
    public static void main(String[] args) {
        // Test 1
        int[] test1Arr = {1, 3, 5};
        int test1Num = 2;
        System.out.println("test 1:\narr = " + Arrays.toString(test1Arr) + ", Number: " + test1Num);
        System.out.println("New Location is: " + newLocation(test1Arr, test1Num));
        // Expected: 1

        System.out.println("=============================");

        // Test 2
        int[] test2Arr = {5, 10, 14, 54};
        int test2Num = 1;
        System.out.println("test 2:\narr = " + Arrays.toString(test2Arr) + ", Number: " + test2Num);
        System.out.println("New Location is: " + newLocation(test2Arr, test2Num));
        // Expected: 0

        System.out.println("=============================");

        // Test 3
        int [] test3Arr = {4, 10, 21, 25};
        int test3Num = 30;
        System.out.println("test 3:\narr = " + Arrays.toString(test3Arr) + ", Number: " + test3Num);
        System.out.println("New Location is: " + newLocation(test3Arr, test3Num));
        // Expected: 4
    }
}
