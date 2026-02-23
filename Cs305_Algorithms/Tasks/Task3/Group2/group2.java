package Tasks.Task3.Group2;
import java.util.HashSet;
import java.util.Set;

/**
 * PROBLEM STATEMENT:
 * Given a string, determine if all characters in the string are unique
 * (no repeated characters). Return true if all characters are distinct,
 * false if any character appears more than once.
 *
 * METHOD:
 * 1. Initialize an empty HashSet to track seen characters
 * 2. Iterate through each character in the input string
 * 3. For each character:
 *    - If already in the set: return false immediately (duplicate found)
 *    - If not in set: add it to the set and continue
 * 4. If loop completes without finding duplicates: return true
 *
 * COMPLEXITY ANALYSIS:
 * - Time Complexity: O(n), where n = length of the string
 *   (Single pass, early termination on first duplicate)
 * - Space Complexity: O(k), where k = number of unique characters
 *   (Set storage for characters, bounded by charset size)
 * - Best Case: O(1) when first two characters are identical
 * - Worst Case: O(n) when all characters are unique or duplicate is last
 */
public class group2 {

    /**
     * Checks if a string has all unique characters (no duplicates)
     * @param str The input string to check
     * @return true if all characters are unique, false otherwise
     */
    public static boolean hasAllUniqueCharacters(String str) {
        // Edge cases: null, empty, or single character strings are unique
        if (str == null || str.length() <= 1) {
            return true;
        }

        Set<Character> seen = new HashSet<>();

        for (char c : str.toCharArray()) {
            // If character already exists in set, we found a duplicate
            if (c != ' ' && !seen.add(c)) {
                return false; // Early exit on first duplicate
            }
        }

        return true; // No duplicates found
    }

    // Example usage and testing
    public static void main(String[] args) {
        // Test cases
        String[] testCases = {"abcdef", "hello", "world", "aA", "  ", ""};

        for (String test : testCases) {
            boolean result = hasAllUniqueCharacters(test);
            System.out.printf("\"%s\" → %s%n", test, result);
        }
    }
}
