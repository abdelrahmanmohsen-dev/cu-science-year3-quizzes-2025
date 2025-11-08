import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] S = {12, 5, 7, 3, 10}; // Original unsorted array
        System.out.println("Original array: " + Arrays.toString(S)); // Print original array

        quickSort(S, 0, S.length - 1); // Call quick sort

        System.out.println("Sorted array: " + Arrays.toString(S)); // Print sorted array
    }

    public static int partition(int[] arr, int left, int right) {
        int pivot = arr[left]; // Choose the first element as pivot
        int j = left; // Pointer for the smaller element

        // Rearrange elements around pivot
        for (int i = left + 1; i <= right; i++) {
            if (arr[i] < pivot) {
                j++;
                swap(arr, i, j); // Swap if element is smaller than pivot
            }
        }

        swap(arr, left, j); // Place pivot in the correct position
        return j; // Return pivot index
    }

    static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int point = partition(arr, left, right); // Partition array
            quickSort(arr, left, point - 1); // Sort left subarray
            quickSort(arr, point + 1, right); // Sort right subarray
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i]; // Swap elements at indices i and j
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
