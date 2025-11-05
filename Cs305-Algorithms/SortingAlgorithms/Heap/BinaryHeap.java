package SortingAlgorithms.Heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Implements a generic Binary Heap data structure using a resizable array.
 * <p>
 * This class can be configured as a Min-Heap or a Max-Heap by providing
 * a java.util.Comparator in its constructor. It serves as the base
 * class for the user-friendly MinHeap and MaxHeap classes.
 *
 * @param <T> The type of element to be stored.
 */
public class BinaryHeap<T> {

    /**
     * The current maximum size of the 'items' array.
     */
    private int CAPACITY = 10;

    /**
     * The current number of elements stored in the heap.
     */
    private int size = 0;

    /**
     * The object responsible for comparing elements.
     * This defines whether it's a Min-Heap or Max-Heap.
     */
    private final Comparator<? super T> comparator;

    /**
     * The array used to store the heap elements.
     */
    @SuppressWarnings("unchecked")
    private T[] items = (T[]) new Object[CAPACITY];

    /**
     * Constructs a new BinaryHeap.
     *
     * @param comparator The strategy used to compare elements.
     */
    public BinaryHeap(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    /**
     * Prints a string representation of the heap's internal array.
     */
    public void print() {
        if (size == 0) {
            System.out.println("[]");
            return;
        }
        System.out.print("[");
        // Loop up to the second-to-last element
        for (int i = 0; i < size - 1; i++) {
            System.out.print(items[i] + ", ");
        }
        // Print the last element (to avoid a trailing comma)
        System.out.println(items[size - 1] + "]");
    }

    /**
     * Swaps two elements in the 'items' array given their indices.
     *
     * @param index1 The index of the first element to swap.
     * @param index2 The index of the second element to swap.
     */
    private void swap(int index1, int index2) {
        T temp = items[index1];
        items[index1] = items[index2];
        items[index2] = temp;
    }

    /**
     * Checks if the heap is full and doubles its capacity if needed.
     */
    private void ensureExtraCapacity() {
        // If the number of elements equals the capacity...
        if (size == CAPACITY) {
            // ...create a new array with double the size
            items = Arrays.copyOf(items, 2 * CAPACITY);
            // ...and update the capacity variable.
            CAPACITY *= 2;
        }
    }

    /**
     * Returns the root element (min or max) without removing it.
     *
     * @return The root element in the heap.
     * @throws NoSuchElementException if the heap is empty.
     */
    public T peek() {
        if (size == 0) throw new NoSuchElementException("Heap is empty");
        // The root element is always at index 0
        return items[0];
    }

    /**
     * Removes and returns the root element (min or max) from the heap.
     * The heap structure is maintained after removal.
     *
     * @return The root element (the original root).
     * @throws NoSuchElementException if the heap is empty.
     */
    public T poll() {
        if (size == 0) throw new NoSuchElementException("Heap is empty");

        // 1. Get the root item to return later
        T item = items[0];

        // 2. Move the last element in the heap to the root position
        items[0] = items[size - 1];
        items[size - 1] = null; // Help garbage collector
        size--; // Decrease the size

        // 3. "Bubble down" the new root to its correct position
        heapifyDown();

        return item;
    }

    /**
     * Adds a new element to the heap, maintaining the heap property.
     *
     * @param item The item to add.
     */
    public void add(T item) {
        // 1. Make sure there is space in the array
        ensureExtraCapacity();

        // 2. Add the new item to the end of the array (the next open spot)
        items[size] = item;
        size++;

        // 3. "Bubble up" the new item to its correct position
        heapifyUp();
    }

    /**
     * Restores the heap property by "bubbling up" the element at the
     * last position (the one just added) to its correct spot.
     */
    private void heapifyUp() {
        int index = size - 1; // Start at the last element

        // While the node has a parent AND the parent is "greater" than the node...
        // (The comparator defines what "greater" means)
        while (hasParent(index) && comparator.compare(Parent(index), items[index]) > 0) {
            // ...swap the node with its parent.
            swap(index, getParentIndex(index));
            // Move up to the parent's index to continue checking
            index = getParentIndex(index);
        }
    }

    /**
     * Restores the heap property by "bubbling down" the element at the
     * root (index 0) to its correct spot.
     */
    private void heapifyDown() {
        int index = 0; // Start at the root

        // As long as the node has at least one child (a left child)
        while (hasLeftChild(index)) {
            // Assume the left child is the "better" one (min or max)
            int bestChildIndex = getLeftChildIndex(index);

            // Check if a right child exists AND is "better" than the left child.
            // (The comparator defines what "better" means)
            if (hasRightChild(index) && comparator.compare(RightChild(index), LeftChild(index)) < 0) {
                bestChildIndex = getRightChildIndex(index);
            }

            // If the parent is already "better" than its best child,
            // the heap property is satisfied and we can stop.
            if (comparator.compare(items[index], items[bestChildIndex]) < 0) {
                break;
            }
            // Otherwise, swap the parent with its best child
            else {
                swap(index, bestChildIndex);
            }

            // Move down to the child's index to continue bubbling down
            index = bestChildIndex;
        }
    }

    /**
     * Drains the heap and returns a new array containing
     * all elements in sorted order.
     * If this is a MinHeap, the order is ascending.
     * If this is a MaxHeap, the order is descending.
     * <p>
     * Note: This method empties the heap.
     *
     * @return A new, sorted array.
     */
    public T[] getSortedArray() {
        // Create a new array to hold the sorted elements
        @SuppressWarnings("unchecked")
        T[] sortedArray = (T[]) new Object[size];

        int originalSize = size; // Store original size, as poll() will change it

        // Poll every element from the heap into the new array
        for (int i = 0; i < originalSize; i++) {
            sortedArray[i] = this.poll();
        }

        return sortedArray;
    }

    // --- Helper Methods (Index and Node Access) ---

    /**
     * Calculates the array index of a parent's left child.
     */
    private int getLeftChildIndex(int parentIndex) { return parentIndex * 2 + 1; }

    /**
     * Calculates the array index of a parent's right child.
     */
    private int getRightChildIndex(int parentIndex) { return parentIndex * 2 + 2; }

    /**
     * Calculates the array index of a child's parent.
     */
    private int getParentIndex(int childIndex) { return (childIndex - 1) / 2; }

    /**
     * Checks if a node at a given index has a left child.
     */
    private boolean hasLeftChild(int index) { return getLeftChildIndex(index) < size; }

    /**
     * Checks if a node at a given index has a right child.
     */
    private boolean hasRightChild(int index) { return getRightChildIndex(index) < size; }

    /**
     * Checks if a node at a given index has a parent.
     */
    private boolean hasParent(int index) { return getParentIndex(index) >= 0; }

    /**
     * Gets the value of the left child of a node.
     */
    private T LeftChild(int index) { return items[getLeftChildIndex(index)]; }

    /**
     * Gets the value of the right child of a node.
     */
    private T RightChild(int index) { return items[getRightChildIndex(index)]; }

    /**
     * Gets the value of the parent of a node.
     */
    private T Parent(int index) { return items[getParentIndex(index)]; }
}