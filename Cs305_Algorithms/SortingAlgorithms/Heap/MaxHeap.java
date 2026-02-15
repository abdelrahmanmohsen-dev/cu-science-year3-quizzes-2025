package SortingAlgorithms.Heap;

import java.util.Comparator;

/**
 * A user-friendly Max-Heap.
 * Automatically configures the BinaryHeap base class to
 * keep the largest element at the root.
 *
 * @param <T> The type of element to be stored (must be Comparable).
 */
public class MaxHeap<T extends Comparable<T>> extends BinaryHeap<T> {

    /**
     * Creates a new Max-Heap.
     */
    public MaxHeap() {
        // "super" calls the constructor of the parent class (BinaryHeap)
        // We pass it the "reverse order" comparator (e.g., 3, 2, 1...)
        super(Comparator.reverseOrder());
    }
}