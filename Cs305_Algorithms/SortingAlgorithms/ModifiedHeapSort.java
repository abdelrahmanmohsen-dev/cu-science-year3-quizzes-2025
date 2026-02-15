package SortingAlgorithms;

/**
 * The ModifiedHeapSort class provides two implementations of the Heap Sort
 * algorithm:
 * 1. Standard Heap Sort (Ascending order) using a Max-Heap.
 * 2. Min-Heap Sort (Descending order) using a Min-Heap.
 * * Heap Sort has a time complexity of O(n log n) in all cases (best, average, worst).
 */
public class ModifiedHeapSort {

    /**
     * Sorts an array of integers in **ascending** order using the Heap Sort algorithm
     * based on a **Max-Heap**.
     * * The sorting process involves two main steps:
     * 1. Building the initial Max-Heap (Heapify).
     * 2. Repeatedly extracting the maximum element (root) and rebuilding the heap.
     * @param arr The input array to be sorted.
     * @return A new array containing the sorted elements in ascending order.
     */
    public static int[] heapSort(int[] arr) {
        // Handle edge cases: null array or array with 0 or 1 element.
        if (arr == null || arr.length <= 1) {
            return arr.clone(); // Return a copy of the original array.
        }

        int[] SortedArray = arr.clone(); // Work on a copy to keep the original array unchanged.
        int n = SortedArray.length;

        // --- Step 1: Build the Max-Heap (initial heapify phase) ---
        // Start from the last non-leaf node (index n/2 - 1) and work up to the root (index 0).
        // This ensures the entire array satisfies the max-heap property.
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(SortedArray, n, i);
        }

        // --- Step 2: Extract elements one by one from the heap ---
        // The loop runs from the last index down to the second element (index 1).
        for (int i = n - 1; i > 0; i--) {
            // Move the current root (the maximum element) to the end of the unsorted portion (index i).
            // This is where the largest element is placed in its final sorted position.
            swap(SortedArray, 0, i);

            // Call max-heapify on the reduced heap (size 'i').
            // The root (index 0) might violate the max-heap property after the swap.
            heapify(SortedArray, i, 0);
        }

        return SortedArray;
    }

    /**
     * Performs the **Max-Heapify** operation on a subtree rooted at index 'i'.
     * This function assumes the children (if they exist) are already max-heaps
     * and ensures the Max-Heap property is maintained at node 'i'.
     * * @param arr The array representing the heap.
     * @param n The size of the heap (the unsorted part of the array).
     * @param i The index of the root of the subtree to heapify.
     */
    private static void heapify(int[] arr, int n, int i) {
        int largest = i;          // Initialize largest as root
        int left = 2 * i + 1;     // Left child index
        int right = 2 * i + 2;    // Right child index

        // Check if left child exists and is greater than current largest
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        // Check if right child exists and is greater than current largest
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        // If largest is not the root (i), swap and recursively heapify the affected subtree.
        if (largest != i) {
            swap(arr, i, largest);

            // Recursively heapify the sub-tree rooted at the new largest index.
            heapify(arr, n, largest);
        }
    }

    /**
     * Helper method to swap two elements in an array.
     * * @param arr The array.
     * @param i The index of the first element.
     * @param j The index of the second element.
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // ------------------------------------------------------------------------
    // MIN-HEAP VARIANT (for Descending Order Sort)
    // ------------------------------------------------------------------------

    /**
     * Sorts an array of integers in **descending** order using the Heap Sort algorithm
     * based on a **Min-Heap**.
     * * The logic is symmetric to the max-heap sort:
     * 1. Build the initial Min-Heap.
     * 2. Repeatedly extract the minimum element (root) and rebuild the heap.
     * @param arr The input array to be sorted.
     * @return A new array containing the sorted elements in descending order.
     */
    public static int[] heapSortMin(int[] arr) {
        // Handle edge cases: null array or array with 0 or 1 element.
        if (arr == null || arr.length <= 1) {
            return arr.clone();
        }

        int[] SortedArray = arr.clone(); // Work on a copy.
        int n = SortedArray.length;

        // --- Step 1: Build the Min-Heap (initial heapify phase) ---
        // Start from the last non-leaf node and work up to the root.
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapifyMin(SortedArray, n, i);
        }

        // --- Step 2: Extract elements one by one from the heap ---
        // Extract the minimum element (root) and place it at the end of the unsorted section.
        // Since we are using a Min-Heap, the smallest element is placed at index 'i',
        // leading to a descending-order sort when read from index 0.
        for (int i = n - 1; i > 0; i--) {
            // Move the current root (the minimum element) to index 'i'.
            swap(SortedArray, 0, i);

            // Call min-heapify on the reduced heap (size 'i').
            heapifyMin(SortedArray, i, 0);
        }

        return SortedArray;
    }


    /**
     * Performs the **Min-Heapify** operation on a subtree rooted at index 'i'.
     * This function ensures the Min-Heap property is maintained at node 'i'
     * by finding the smallest among the root, left, and right children.
     * @param arr The array representing the heap.
     * @param n The size of the heap (the unsorted part of the array).
     * @param i The index of the root of the subtree to heapify.
     */
    private static void heapifyMin(int[] arr, int n, int i) {
        int smallest = i;          // Initialize smallest as root
        int left = 2 * i + 1;      // Left child index
        int right = 2 * i + 2;     // Right child index

        // Check if left child exists and is smaller than current smallest
        if (left < n && arr[left] < arr[smallest]) {
            smallest = left;
        }

        // Check if right child exists and is smaller than current smallest
        if (right < n && arr[right] < arr[smallest]) {
            smallest = right;
        }

        // If smallest is not the root (i), swap and recursively heapify the affected subtree.
        if (smallest != i) {
            swap(arr, i, smallest);

            // Recursively heapify the sub-tree rooted at the new smallest index.
            heapifyMin(arr, n, smallest);
        }
    }
}