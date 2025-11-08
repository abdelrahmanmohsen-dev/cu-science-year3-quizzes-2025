/*
Problem: The Tallest String (Longest String Finder)
------------------------------------------------------------
Description:
Given an array of strings, find and return the string
with the greatest length using a divide and conquer approach.

If two strings have the same maximum length, return the one
that appears first (on the left side).

Example:
Input: ["cat", "giraffe", "lion", "elephant"]
Output: "elephant"

Approach:
- Divide the array into two halves.
- Recursively find the longest string in each half.
- Compare their lengths and return the longer one.
*/

public class TheTallestString {

    public static void main(String[] args) {
        // Test Case 1
        String[] arr1 = {"cat", "giraffe", "lion", "elephant"};
        System.out.println("Test 1: " + tallestString(arr1, 0, arr1.length - 1));
        // Expected: elephant

        // Test Case 2
        String[] arr2 = {"apple", "banana", "pear", "kiwi"};
        System.out.println("Test 2: " + tallestString(arr2, 0, arr2.length - 1));
        // Expected: banana

        // Test Case 3
        String[] arr3 = {"a", "bb", "ccc", "dddd", "ee"};
        System.out.println("Test 3: " + tallestString(arr3, 0, arr3.length - 1));
        // Expected: dddd

        // Test Case 4 (Equal length)
        String[] arr4 = {"sun", "sky", "sea"};
        System.out.println("Test 4: " + tallestString(arr4, 0, arr4.length - 1));
        // Expected: sun (since all equal length, returns first)
    }

    // Recursive function to find the longest string using divide and conquer
    public static String tallestString(String[] s, int l, int r) {
        if (l == r)
            return s[l]; // Base case: only one string

        int mid = l + (r - l) / 2; // Find midpoint

        // Recursively find longest string in left and right halves
        String left = tallestString(s, l, mid);
        String right = tallestString(s, mid + 1, r);

        // Compare lengths and return the longer string
        if (left.length() == right.length())
            return left; // If equal, keep the left one (appears first)
        else if (left.length() > right.length())
            return left;
        else
            return right;
    }
}

