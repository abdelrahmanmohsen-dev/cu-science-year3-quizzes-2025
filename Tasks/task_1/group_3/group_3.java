package Tasks.task_1.group_3;

public class group_3 {
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

    // testCases
    public static void main(String[] args) {
        //Test1
        System.out.println(newLocation(new int[]{1, 3, 5, 7}, 2));
        // Expected: 1

        //Test2
        System.out.println(newLocation(new int[]{5, 10, 87, 90}, 1));
        // Expected: 0

        //Test3
        System.out.println(newLocation(new int[]{4, 5, 18, 20}, 25));
        // Expected: 4
    }
}
