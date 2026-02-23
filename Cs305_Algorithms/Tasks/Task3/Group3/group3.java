package Tasks.Task3.Group3;

import java.util.HashMap;
import java.util.Map;

/**
 * PROBLEM STATEMENT:
 * Given an integer, determine if all digits in the number are unique
 * (no repeated digits). Return true if all digits are distinct,
 * false if any digit appears more than once.
 *
 * METHOD (Using Map):
 * 1. Handle edge case: negative numbers (convert to positive or handle sign)
 * 2. Initialize an empty HashMap<Integer, Boolean> to track seen digits
 * 3. Extract digits one by one using modulo 10 and division by 10
 * 4. For each digit:
 *    - Check if digit exists as key in map (digit previously seen)
 *    - If exists: return false immediately (duplicate found)
 *    - If not exists: put(digit, true) and continue
 * 5. If all digits processed without duplicates: return true
 *
 * COMPLEXITY ANALYSIS:
 * - Time Complexity: O(d), where d = number of digits in the integer
 *   (Single pass through all digits, early termination on first duplicate)
 * - Space Complexity: O(k), where k = number of unique digits (max 10 for 0-9)
 *   (Map storage bounded by 10 possible digits, effectively O(1))
 * - Best Case: O(1) when first two digits are identical
 * - Worst Case: O(d) when all digits are unique or duplicate is last
 * - Note: For integers, space is effectively O(1) since only 10 digits exist
 */
public class group3 {

    /**
     * Checks if an integer has all unique digits using Map
     * @param num The input integer to check
     * @return true if all digits are unique, false otherwise
     */
    public static boolean hasAllUniqueDigits(int num) {
        // Handle edge case: single digit or zero is always unique
        if (num >= 0 && num < 10) {
            return true;
        }

        // Handle negative numbers by taking absolute value
        int n = Math.abs(num);

        Map<Integer, Boolean> seenDigits = new HashMap<>();

        while (n > 0) {
            int digit = n % 10;  // Extract last digit
            n = n / 10;          // Remove last digit

            // Check if digit already exists in map
            if (seenDigits.containsKey(digit)) {
                return false; // Duplicate digit found, early exit
            }
            // Mark digit as seen
            seenDigits.put(digit, true);
        }

        return true; // No duplicate digits found
    }

    // Example usage and testing
    public static void main(String[] args) {
        // Test cases
        int[] testCases = {12345, 112345, 987654321, 0, 5, -1234, -1123, 111111, 101};

        System.out.println("Checking for unique digits using Map:");
        for (int test : testCases) {
            boolean result = hasAllUniqueDigits(test);
            System.out.printf("%d → %s%n", test, result);
        }
    }

    /**
     * You don't have to know that, but it's for how want to learn
     * Bit manipulation approach - O(1) space, no Map needed
     * Uses a single int as a 10-bit flag (one bit per digit 0-9)
     */
    public static boolean hasAllUniqueDigitsBitwise(int num) {
        int n = Math.abs(num);
        int seen = 0; // 10 bits are enough for digits 0-9

        while (n > 0) {
            int digit = n % 10;
            int bitMask = 1 << digit;

            if ((seen & bitMask) != 0) {
                return false; // Bit already set, duplicate found
            }
            seen |= bitMask; // Set the bit for this digit
            n /= 10;
        }
        return true;
    }
}
