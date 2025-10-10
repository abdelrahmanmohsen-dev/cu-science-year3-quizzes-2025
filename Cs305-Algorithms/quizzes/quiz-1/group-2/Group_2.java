/*
 * ==============================================================
 *  Problem Name: Remove Duplicates from Array
 * ==============================================================
 *  Description:
 *  ==>> Write an algorithm that removes the duplicates of an array
 *  and returns the new array without changing the original one.
 *  ==============================================================
 *  Example:
 *  Input:  [1, 2, 2, 3, 4, 4, 5]
 *  Output: [1, 2, 3, 4, 5]
 */

import java.util.*;

public class Group_2 {

    /**
     * --------------------------------------------------------------
     *  Method 1: Using HashSet
     * --------------------------------------------------------------
     * Removes duplicates by storing unique values in a HashSet.
     * Fast and simple, but doesn't preserve the input order.
     */
    public static int[] removeDuplicates(int[] arr) {
        HashSet<Integer> set = new HashSet<>();

        for (int element : arr) {
            set.add(element);
        }

        int[] result = new int[set.size()];
        int index = 0;
        for (int item : set) {
            result[index++] = item;
        }

        return result;
    }

    /**
     * --------------------------------------------------------------
     *  Method 2: Manual Approach using ArrayList
     * --------------------------------------------------------------
     * Checks each element manually before adding.
     * Preserves order of appearance but slower (O(n²)).
     */
    public static int[] removeDuplicatesManual(int[] arr) {
        ArrayList<Integer> list = new ArrayList<>();

        for (int element : arr) {
            if (!list.contains(element)) {
                list.add(element);
            }
        }

        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;
    }

    /**
     * --------------------------------------------------------------
     *  Method 3: Using Sorting
     * --------------------------------------------------------------
     * Sorts the array first, then skips consecutive duplicates.
     * Simple and efficient if order doesn't matter.
     */
    public static int[] removeDuplicatesSorted(int[] arr) {
        if (arr.length == 0) return new int[0];

        Arrays.sort(arr);
        int uniqueCount = 1;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[i - 1]) {
                uniqueCount++;
            }
        }

        int[] result = new int[uniqueCount];
        result[0] = arr[0];
        int index = 1;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[i - 1]) {
                result[index++] = arr[i];
            }
        }

        return result;
    }

    /**
     * --------------------------------------------------------------
     *  Method 4: Manual In-Place Copy (Without Changing Original)
     * --------------------------------------------------------------
     * Copies the array and removes duplicates in-place.
     * Doesn't use extra data structures and keeps the original intact.
     */
    public static int[] removeDuplicatesInPlace(int[] arr) {
        int[] copy = Arrays.copyOf(arr, arr.length);
        int count = 0;

        for (int i = 0; i < copy.length; i++) {
            boolean duplicated = false;
            for (int j = 0; j < count; j++) {
                if (copy[i] == copy[j]) {
                    duplicated = true;
                    break;
                }
            }
            if (!duplicated) {
                copy[count++] = copy[i];
            }
        }

        return Arrays.copyOfRange(copy, 0, count);
    }

    /** Helper method to print arrays for test results. */
    public static void printArray(String testName, int[] arr) {
        System.out.println(testName + " → " + Arrays.toString(arr));
    }

    /** --------------------------------------------------------------
     *  Test Cases
     * --------------------------------------------------------------
     */
    public static void main(String[] args) {
        // ===== Method 1 Tests =====
        System.out.println("=== Method 1: Using HashSet ===");
        printArray("Test 1", removeDuplicates(new int[]{1, 2, 2, 3, 4, 4, 5}));
        printArray("Test 2", removeDuplicates(new int[]{7, 7, 7, 7}));
        printArray("Test 3", removeDuplicates(new int[]{10, 20, 10, 30, 20, 40}));

        // ===== Method 2 Tests =====
        System.out.println("\n=== Method 2: Manual ArrayList ===");
        printArray("Test 1", removeDuplicatesManual(new int[]{1, 2, 2, 3, 4, 4, 5}));
        printArray("Test 2", removeDuplicatesManual(new int[]{5, 5, 6, 6, 7}));
        printArray("Test 3", removeDuplicatesManual(new int[]{10, 10, 20, 30, 10, 30}));

        // ===== Method 3 Tests =====
        System.out.println("\n=== Method 3: Using Sorting ===");
        printArray("Test 1", removeDuplicatesSorted(new int[]{4, 2, 2, 3, 4, 5, 1}));
        printArray("Test 2", removeDuplicatesSorted(new int[]{9, 9, 9}));
        printArray("Test 3", removeDuplicatesSorted(new int[]{5, 2, 8, 5, 2, 1, 9}));

        // ===== Method 4 Tests =====
        System.out.println("\n=== Method 4: Manual In-Place Copy ===");
        printArray("Test 1", removeDuplicatesInPlace(new int[]{1, 2, 2, 3, 4, 4, 5}));
        printArray("Test 2", removeDuplicatesInPlace(new int[]{7, 7, 7, 7}));
        printArray("Test 3", removeDuplicatesInPlace(new int[]{10, 20, 10, 30, 20, 40}));
    }
}
