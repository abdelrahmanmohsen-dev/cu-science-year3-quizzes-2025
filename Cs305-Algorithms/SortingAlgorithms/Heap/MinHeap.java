package SortingAlgorithms.Heap;

import java.util.Comparator;

/**
 * A user-friendly Min-Heap.
 * Automatically configures the BinaryHeap base class to
 * keep the smallest element at the root.
 *
 * @param <T> The type of element to be stored (must be Comparable).
 */
public class MinHeap<T extends Comparable<T>> extends BinaryHeap<T> {

    /**
     * Creates a new Min-Heap.
     */
    public MinHeap() {
        // "super" calls the constructor of the parent class (BinaryHeap)
        // We pass it the "natural order" comparator (e.g., 1, 2, 3...)
        super(Comparator.naturalOrder());
    }
}