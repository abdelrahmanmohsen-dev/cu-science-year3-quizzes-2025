package Quizzes.Quiz_1.Group2;

import java.util.*;

/**
 * ==============================================================
 *  Problem Name: Remove Duplicates from Array (Generic - Optimized)
 * ==============================================================
 *  Description:
 *  ==>> Write a generic algorithm that removes duplicates from an array
 *  of any type T, preserves original order, and returns a List<T>
 *  without modifying the original.
 *
 *  Method: Uses HashSet for O(1) duplicate checks + ArrayList for ordered result
 *
 *  Complexity:
 *  - Time: O(n) - single pass, O(1) contains() with HashSet
 *  - Space: O(k) - where k is number of unique elements (Set + List)
 *
 *  Example:
 *  Input:  [1, 2, 2, 3, 4, 4, 5]
 *  Output: [1, 2, 3, 4, 5]
 */

public class group2_generic {

    /**
     * Removes duplicates from array while preserving order - O(n) time
     * @param arr Input array of any type T
     * @return List<T> with duplicates removed, order preserved
     */
    public static <T> List<T> removeDuplicates(T[] arr) {
        if (arr == null) {
            return new ArrayList<>();
        }

        Set<T> seen = new HashSet<>();      // O(1) contains() lookup
        List<T> result = new ArrayList<>();  // Preserves insertion order

        for (T element : arr) {
            if (seen.add(element)) {         // add() returns false if already present
                result.add(element);         // Only add to result if unique
            }
        }

        return result;
    }

    /** Helper method to print results */
    public static <T> void printResult(String testName, T[] arr) {
        System.out.println(testName + " → " + removeDuplicates(arr));
    }

    /** Test Cases - Same as first answer */
    public static void main(String[] args) {

        // ===== Original Tests =====
        System.out.println("=== Optimized O(n) Solution ===");
        printResult("Test 1", new Integer[]{1, 2, 2, 3, 4, 4, 5});
        printResult("Test 2", new Integer[]{7, 7, 7, 7});
        printResult("Test 3", new Integer[]{10, 20, 10, 30, 20, 40});

        // ===== Additional Tests =====
        System.out.println("\n=== String Tests ===");
        printResult("Test 4", new String[]{"apple", "banana", "apple", "cherry"});
        printResult("Test 5", new String[]{"hello", "world", "hello", "java"});

        // ===== Edge Cases =====
        System.out.println("\n=== Edge Cases ===");
        printResult("Empty array", new Integer[]{});
        printResult("Single element", new String[]{"only"});
        printResult("No duplicates", new Integer[]{1, 2, 3, 4, 5});
        printResult("All same", new Integer[]{9, 9, 9, 9, 9});

        System.out.println("Null array → " + removeDuplicates(null));
    }
}