import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7}; // Original unsorted array
        System.out.println("Original array:" + Arrays.toString(arr)); // Print original array
        mergeSort(arr, arr.length); // Call merge sort

        System.out.println("Sorted array:" + Arrays.toString(arr)); // Print sorted array
    }

    public static void merge(int[] u, int[] v, int[] s) {
        int n = u.length; // Length of first subarray
        int m = v.length; // Length of second subarray
        int i = 0, j = 0, k = 0; // Indices for u, v, and s

        // Merge elements from u and v into s
        while (i < n && j < m) {
            if (u[i] < v[j]) {
                s[k++] = u[i++];
            } else
                s[k++] = v[j++];
        }

        // Copy remaining elements of u, if any
        while (i < n) {
            s[k++] = u[i++];
        }

        // Copy remaining elements of v, if any
        while (j < m) {
            s[k++] = v[j++];
        }
    }

    static void mergeSort(int[] s, int n) {
        if (n > 1) {
            int h = n / 2, m = n - h; // Split array into two halves
            int[] u = new int[h]; // First half
            int[] v = new int[m]; // Second half

            // Copy elements into u
            for (int i = 0; i < h; i++) {
                u[i] = s[i];
            }

            // Copy elements into v
            for (int i = 0; i < m; i++) {
                v[i] = s[h + i];
            }

            mergeSort(u, h); // Recursively sort first half
            mergeSort(v, m); // Recursively sort second half
            merge(u, v, s); // Merge sorted halves back into s
        }
    }
}
