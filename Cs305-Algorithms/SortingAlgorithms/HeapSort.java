package SortingAlgorithms;

import SortingAlgorithms.Heap.BinaryHeap;
import SortingAlgorithms.Heap.MaxHeap;

public class HeapSort {

    /**
     * Sorts an array in ascending order using a MinHeap.
     * This implementation avoids the ClassCastException by
     * polling elements back into the original array.
     *
     * @param <T> The type of element (must be Comparable).
     * @param array The array to be sorted.
     */
    public static <T extends Comparable<T>> void sort(T[] array) {
        if (array == null || array.length < 2)
            return;

        // 1. Create a MinHeap to sort in ascending order
        BinaryHeap<T> maxHeap = new MaxHeap<>();

        // 2. Add all elements from the array into the heap
        for (T element : array) {
            maxHeap.add(element);
        }

        // 3. Poll all elements from the heap *back into the original array*
        // This is the safe and correct way.
        for (int i = array.length - 1; i >= 0; i--) {
            array[i] = maxHeap.poll();
        }
    }
}