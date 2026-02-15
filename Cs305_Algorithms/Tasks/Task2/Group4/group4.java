package Tasks.Task2.Group4;

import java.util.Arrays;

/**
 * Problem:
 * - Check if two integer arrays are identical (same length and same elements
 * at corresponding positions).
 * <p>
 * Time Complexity:
 * O(N) - where N is the length of the arrays.
 * Both solutions must visit every element in the worst case
 * (if the arrays are identical or differ at the last element).
 */
public class group4 {

    // --- Method 1: Linear Recursion ---

    /**
     * Method 1 (Public Wrapper): Checks if two arrays are identical.
     * <p>
     * First checks if the lengths are equal. If they are, it calls
     * a private recursive helper to check the elements one by one.
     *
     * @param arr1 The first array.
     * @param arr2 The second array.
     * @return true if the arrays are identical, false otherwise.
     */
    public static boolean areIdentical(int[] arr1, int[] arr2) {
        // The main check: must have the same length.
        if (arr1.length != arr2.length) {
            return false;
        }
        // Start the recursive check at the first index (0).
        return areIdenticalRecursive(arr1, arr2, 0);
    }

    /**
     * Private Helper for Linear Recursion: Checks one element at a time.
     * <p>
     * NOTE: This method ASSUMES arr1.length == arr2.length.
     *
     * @param arr1 The first array.
     * @param arr2 The second array.
     * @param idx  The current index to check.
     * @return true if elements from idx onwards are identical.
     */
    private static boolean areIdenticalRecursive(int[] arr1, int[] arr2, int idx) {
        // Base case: If we've successfully checked all elements, they are identical.
        if (idx == arr1.length) {
            return true;
        }

        // Recursive step:
        // Check if current elements match AND the rest of the array matches.
        return (arr1[idx] == arr2[idx]) && areIdenticalRecursive(arr1, arr2, idx + 1);
    }

    // --- Method 2: Divide and Conquer ---

    /**
     * Method 2 (Public Wrapper): Checks if two arrays are identical using DAC.
     * <p>
     * First checks if the lengths are equal. If they are, it calls
     * a private recursive helper to check the elements by dividing the array.
     *
     * @param arr1 The first array.
     * @param arr2 The second array.
     * @return true if the arrays are identical, false otherwise.
     */
    public static boolean areIdenticalDAC(int[] arr1, int[] arr2) {
        // The main check: must have the same length.
        if (arr1.length != arr2.length) {
            return false;
        }
        // Start the recursive check on the entire range (0 to length-1).
        // This correctly handles empty arrays (start=0, end=-1), returning true.
        return areIdenticalDACRecursive(arr1, arr2, 0, arr1.length - 1);
    }

    /**
     * Private Helper for DAC: Recursively splits arrays and checks halves.
     * <p>
     * NOTE: This method ASSUMES arr1.length == arr2.length.
     *
     * @param arr1  The first array.
     * @param arr2  The second array.
     * @param start The starting index of the current subarray.
     * @param end   The ending index of the current subarray.
     * @return true if subarrays [start, end] are identical.
     */
    private static boolean areIdenticalDACRecursive(int[] arr1, int[] arr2, int start, int end) {
        // Base case: An empty range (e.g., from an empty array) is identical.
        if (start > end) {
            return true;
        }

        // Base case: If the subarray is a single element
        if (start == end) {
            return arr1[start] == arr2[start];
        }

        // Divide: Calculate the middle index
        int mid = start + (end - start) / 2;

        // Conquer and Combine:
        // Check if the left half is identical AND the right half is identical.
        return areIdenticalDACRecursive(arr1, arr2, start, mid)
                && areIdenticalDACRecursive(arr1, arr2, mid + 1, end);
    }

    public static void main(String[] args) {
        // TestCase 1 (DAC, Identical)
        int[] a1 = {10, 20, 30, 40, 50};
        int[] a2 = {10, 20, 30, 40, 50};
        System.out.println("DAC Check on:");
        System.out.println("Arr1: " + Arrays.toString(a1));
        System.out.println("Arr2: " + Arrays.toString(a2));
        boolean res1 = areIdenticalDAC(a1, a2); // Simplified call
        System.out.println("is: " + (res1? "Identical" : "Not Identical"));
        // Expected: Identical

        System.out.println("================================");

        // TestCase 2 (Linear, Not Identical - different element)
        int[] b1 = {1, 2, 5, 4, 6};
        int[] b2 = {1, 2, 9, 4, 6}; // Difference at index 2
        System.out.println("Linear Check on:");
        System.out.println("Arr1: " + Arrays.toString(b1));
        System.out.println("Arr2: " + Arrays.toString(b2));
        boolean res2 = areIdentical(b1, b2); // Simplified call
        System.out.println("is: " + (res2? "Identical" : "Not Identical"));
        // Expected: Not Identical

        System.out.println("================================");

        // TestCase 3 (DAC, Not Identical - different length)
        int[] c1 = {1, 2, 3};
        int[] c2 = {1, 2, 3, 4};
        System.out.println("DAC Check on:");
        System.out.println("Arr1: " + Arrays.toString(c1));
        System.out.println("Arr2: " + Arrays.toString(c2));
        boolean res3 = areIdenticalDAC(c1, c2); // Simplified call
        System.out.println("is: " + (res3? "Identical" : "Not Identical"));
        // Expected: Not Identical

        System.out.println("================================");

        // TestCase 4 (Linear, Empty Arrays)
        int[] d1 = {};
        int[] d2 = {};
        System.out.println("Linear Check on:");
        System.out.println("Arr1: " + Arrays.toString(d1));
        System.out.println("Arr2: " + Arrays.toString(d2));
        boolean res4 = areIdentical(d1, d2); // Simplified call
        System.out.println("is: " + (res4? "Identical" : "Not Identical"));
        // Expected: Identical
    }
}