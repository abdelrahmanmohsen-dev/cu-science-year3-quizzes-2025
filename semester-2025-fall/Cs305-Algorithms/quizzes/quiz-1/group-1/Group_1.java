public class Group_1 {

    /**
     * Finds the most frequently occurring character in a given string.
     * The comparison is case-insensitive and considers all characters
     * (letters, digits, symbols, spaces, etc.).
     *
     * @param str The input string to analyze.
     * @return The character that occurs most frequently in the string.
     * @throws IllegalArgumentException If the string is null, empty, or all characters are unique.
     */
    public static char mostOccurrence(String str) {
        // Validate input
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be null or empty");
        }

        // Convert to lowercase for case-insensitive comparison
        str = str.toLowerCase();

        // Frequency array for all ASCII characters
        int[] freq = new int[256];

        // Count frequency of each character
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            freq[c]++;
        }

        // Find character with the maximum occurrence
        int maxCount = 0;
        char mostChar = 0;
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] > maxCount) {
                maxCount = freq[i];
                mostChar = (char) i;
            }
        }

        // If all characters are unique (max frequency = 1), throw an exception
        if (maxCount <= 1) {
            throw new IllegalArgumentException("All characters in the string are unique — no repetitions found.");
        }

        return mostChar;
    }

    /**
     * Main method for testing the algorithm.
     */
    public static void main(String[] args) {
        try {
            // Test 1: Normal sentence with letters and spaces
            System.out.println("Test 1 → " +
                    mostOccurrence("Success consists of going from failure to failure without loss of enthusiasm"));
            // Expected: ' ' (space) or 's'

            // Test 2: Case-insensitive check
            System.out.println("Test 2 → " +
                    mostOccurrence("AaAaBbCc"));
            // Expected: 'a'

            // Test 3: String with numbers
            System.out.println("Test 3 → " +
                    mostOccurrence("122333444455555"));
            // Expected: '5'

            // Test 4: String with symbols
            System.out.println("Test 4 → " +
                    mostOccurrence("$$##@@@!!!"));
            // Expected: '@'

            // Test 5: All unique characters (should trigger exception)
            System.out.println("Test 5 → " +
                    mostOccurrence("abc123!"));
            // Expected: Exception

        } catch (IllegalArgumentException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
