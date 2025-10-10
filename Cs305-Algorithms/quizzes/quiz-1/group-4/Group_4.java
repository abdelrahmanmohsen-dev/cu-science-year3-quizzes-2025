import java.util.Arrays;

public class Group_4 {

    /**
     * Problem:
     * Given two strings, write an algorithm that checks if they are anagrams.
     * Two strings are anagrams if they have the same length and contain
     * the same letters (case-insensitive), regardless of order.
     *
     * Example:
     * "Listen" and "Silent" → true
     * "Hello" and "Olelh"  → true
     * "Test" and "Taste"   → false
     */

    public static boolean areAnagrams(String str1, String str2) {
        // Handle null inputs
        if (str1 == null || str2 == null) {
            throw new IllegalArgumentException("Strings cannot be null");
        }

        // Remove spaces and convert to lowercase for fair comparison
        str1 = str1.replaceAll("\\s+", "").toLowerCase();
        str2 = str2.replaceAll("\\s+", "").toLowerCase();

        // Quick length check
        if (str1.length() != str2.length()) {
            return false;
        }

        // Count frequency of each character (supports only a–z)
        int[] freq = new int[26];
        for (char c : str1.toCharArray()) {
            if (Character.isLetter(c))
                freq[c - 'a']++;
        }

        for (char c : str2.toCharArray()) {
            if (Character.isLetter(c))
                freq[c - 'a']--;
        }

        // If all frequencies return to zero, they’re anagrams
        for (int count : freq) {
            if (count != 0) return false;
        }

        return true;
    }

    /** Helper method to print results clearly */
    public static void printResult(String s1, String s2) {
        System.out.printf("Are '%s' and '%s' anagrams? → %b%n", s1, s2, areAnagrams(s1, s2));
    }

    /** Testing the areAnagrams method */
    public static void main(String[] args) {
        System.out.println("=== Testing areAnagrams Function ===");

        // Test 1: Classic anagram
        printResult("Listen", "Silent");

        // Test 2: Same letters, different order
        printResult("Hello", "Olelh");

        // Test 3: Not anagrams (different letters)
        printResult("Test", "Taste");

        // Test 4: Anagrams with spaces
        printResult("Dormitory", "Dirty room");

        // Test 5: Same letters but different frequency
        printResult("Aabb", "Abab");
    }
}
