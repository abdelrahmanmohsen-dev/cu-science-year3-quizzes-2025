/**
 * Problem: Kth Largest Element in an Array
 * ----------------------------------------
 * Given an integer array nums and an integer k,
 * return the k-th largest element in the array.
 *
 * Example:
 * ----------
 * Input: nums = [3,2,1,5,6,4], k = 2
 * Output: 5
 *
 * This class includes three different approaches:
 * 1. QuickSort (descending order)
 * 2. Counting Sort (descending order)
 * 3. QuickSelect (efficient divide-and-conquer approach)
 */

public class KthLargestElement {

    // ======================== SOLUTION 1 ========================
    // QuickSort approach (Descending)

    /**
     * Sorts the array using QuickSort (descending) and returns the k-th largest element.
     *
     * @param nums array of integers
     * @param k    rank of the largest element (1 = largest)
     * @return     the k-th largest element
     */
    public static int findKthLargestByQuickSort(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            throw new IllegalArgumentException("nums must not be null or empty");
        if (k < 1 || k > nums.length)
            throw new IllegalArgumentException("k is out of range");

        quickSortDescending(nums, 0, nums.length - 1);
        return nums[k - 1]; // since sorted descending
    }

    // Recursive QuickSort (Descending)
    private static void quickSortDescending(int[] arr, int low, int high) {
        if (low < high) {
            int pivot = partitionDescending(arr, low, high);
            quickSortDescending(arr, low, pivot - 1);
            quickSortDescending(arr, pivot + 1, high);
        }
    }

    // ======================== SOLUTION 2 ========================
    // Counting Sort approach (Descending)

    /**
     * Finds the k-th largest element by sorting using Counting Sort (descending).
     *
     * @param nums array of integers (range [-10000, 10000])
     * @param k    rank of the largest element (1 = largest)
     * @return     the k-th largest element
     */
    public static int findKthLargestByCountingSort(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            throw new IllegalArgumentException("nums must not be null or empty");
        if (k < 1 || k > nums.length)
            throw new IllegalArgumentException("k is out of range");

        countingSortDescending(nums);
        return nums[k - 1];
    }

    // Counting Sort implementation (Descending)
    private static void countingSortDescending(int[] arr) {
        int maxSize = 20000;  // range [-10000, 10000]
        int[] freq = new int[maxSize + 1];

        for (int item : arr) {
            freq[item + 10000]++;
        }

        int index = 0;
        for (int i = freq.length - 1; i >= 0; i--) {
            while (freq[i] > 0) {
                arr[index++] = i - 10000;
                freq[i]--;
            }
        }
    }

    // ======================== SOLUTION 3 ========================
    // QuickSelect approach (Descending)

    /**
     * Finds the k-th largest element using QuickSelect (descending partition logic).
     *
     * @param nums array of integers
     * @param k    rank of the largest element (1 = largest)
     * @return     the k-th largest element
     */
    public static int findKthLargestByQuickSelect(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            throw new IllegalArgumentException("nums must not be null or empty");
        if (k < 1 || k > nums.length)
            throw new IllegalArgumentException("k is out of range");

        return binaryPartition(nums, 0, nums.length - 1, k - 1);
    }

    // Recursive Binary Partition (QuickSelect)
    private static int binaryPartition(int[] nums, int low, int high, int kIndex) {
        if (low == high) return nums[low];

        int pivotIndex = low + (int) (Math.random() * (high - low + 1));
        swap(nums, pivotIndex, low);

        int pivotPoint = partitionDescending(nums, low, high);

        if (pivotPoint == kIndex) return nums[pivotPoint];
        else if (pivotPoint < kIndex) return binaryPartition(nums, pivotPoint + 1, high, kIndex);
        else return binaryPartition(nums, low, pivotPoint - 1, kIndex);
    }

    // ======================== COMMON PARTITION ========================
    /**
     * Shared partition logic for both QuickSort and QuickSelect.
     * It arranges elements so that larger ones appear on the left (descending order).
     */
    private static int partitionDescending(int[] arr, int low, int high) {
        int pivot = arr[low];
        int j = low;
        for (int i = low + 1; i <= high; i++) {
            if (arr[i] > pivot) { // '>' keeps descending
                j++;
                swap(arr, j, i);
            }
        }
        swap(arr, low, j);
        return j;
    }

    // Common swap helper
    private static void swap(int[] arr, int i, int j) {
        if (i == j) return;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // ======================== TESTS ========================
    public static void main(String[] args) {
        System.out.println("=== Tests for Kth Largest (all techniques) ===");

        // QuickSort test
        {
            int[] arr = {3, 2, 1, 5, 6, 4};
            int k = 2;
            int[] copy = arr.clone();
            int result = findKthLargestByQuickSort(copy, k);
            System.out.println("QuickSort Test: [3,2,1,5,6,4], k=2 → Result=" + result + " | Expected=5");
        }

        // CountingSort test
        {
            int[] arr = {0, -1, 5, 3, 3};
            int k = 3;
            int[] copy = arr.clone();
            int result = findKthLargestByCountingSort(copy, k);
            System.out.println("CountingSort Test: [0,-1,5,3,3], k=3 → Result=" + result + " | Expected=3");
        }

        // QuickSelect test
        {
            int[] arr = {-1, -5, -3, -4};
            int k = 2;
            int[] copy = arr.clone();
            int result = findKthLargestByQuickSelect(copy, k);
            System.out.println("QuickSelect Test: [-1,-5,-3,-4], k=2 → Result=" + result + " | Expected=-3");
        }
    }
}