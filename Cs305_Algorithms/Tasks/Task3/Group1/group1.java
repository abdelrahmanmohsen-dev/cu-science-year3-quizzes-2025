package Tasks.Task3.Group1;

import java.util.HashMap;
import java.util.Map;

/**
 * PROBLEM STATEMENT:
 * Given a string of characters, return the frequency of every character 
 * in the form of a Map<Character, Integer>, where each key is a unique 
 * character and each value is the count of occurrences of that character.
 *
 * METHOD:
 * 1. Initialize an empty HashMap to store character frequencies
 * 2. Iterate through each character in the input string
 * 3. For each character, update its count in the map using getOrDefault()
 *    - If character exists: increment existing count by 1
 *    - If character doesn't exist: initialize count to 1
 * 4. Return the populated frequency map
 *
 * COMPLEXITY ANALYSIS:
 * - Time Complexity: O(n), where n = length of the string
 *   (Single pass through all characters)
 * - Space Complexity: O(k), where k = number of unique characters
 *   (Storage for each unique character in the map)
 * - Best Case: O(1) for empty/null string
 * - Worst Case: O(n) when all characters are unique
 */
public class group1 {

    /**
     * Returns a Map with each character and its frequency in the given string
     * @param str The input string to analyze
     * @return Map where keys are characters and values are occurrence counts
     */
    public static Map<Character, Integer> getCharFrequency(String str) {
        Map<Character, Integer> frequencyMap = new HashMap<>();

        // Handle null or empty string edge case
        if (str == null || str.isEmpty()) {
            return frequencyMap;
        }

        // Count frequency of each character in a single pass
        for (char c : str.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        return frequencyMap;
    }

    // Example usage and testing
    public static void main(String[] args) {
        String input = "hello world";
        Map<Character, Integer> result = getCharFrequency(input);

        System.out.println("Input: \"" + input + "\"");
        System.out.println("Character Frequencies:");

        // Print each character and its count
        for (Map.Entry<Character, Integer> entry : result.entrySet()) {
            System.out.println("'" + entry.getKey() + "' : " + entry.getValue());
        }

        // Alternative: Print the entire map
        System.out.println("\nFull Map: " + result);
    }
}