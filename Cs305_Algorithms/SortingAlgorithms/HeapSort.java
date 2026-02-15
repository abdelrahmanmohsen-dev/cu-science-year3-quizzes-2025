package SortingAlgorithms;

import SortingAlgorithms.Heap.BinaryHeap;
import SortingAlgorithms.Heap.MaxHeap;

public class HeapSort {

    /**
     * Sorts an array in ascending order using a MaxHeap.
     * Also you can make the same Algorithm using MinHeap with just reversing the last for-loop
     *
     * @param <T> The type of element (must be Comparable).
     * @param array The array to be sorted.
     */
    public static <T extends Comparable<T>> void sort(T[] array) {
        if (array == null || array.length < 2)
            return;

        // 1. Create a MaxHeap to sort in ascending order
        BinaryHeap<T> maxHeap = new MaxHeap<>();

        // 2. Add all elements from the array into the heap
        for (T element : array) {
            maxHeap.add(element);
        }

        // 3. Poll all elements from the heap *back into the original array*
        for (int i = array.length - 1; i >= 0; i--) {
            array[i] = maxHeap.poll();
        }
    }
}
