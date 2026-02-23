package Tasks.Task3.Group4;

import java.util.HashMap;
import java.util.Map;

/**
 * PROBLEM STATEMENT:
 * Given two parallel arrays - one containing candidate names (Strings) and
 * another containing their corresponding votes (integers), return a Map where
 * each key is a unique candidate name and each value is the total sum of all
 * votes that candidate received.
 *
 * Example:
 *   names = {"mohamed", "ahmed", "mohamed"}
 *   votes = {1, 2, 3}
 *   Result: {"mohamed" -> 4, "ahmed" -> 2}
 *
 * METHOD (Using Map):
 * 1. Validate that both arrays have the same length
 * 2. Initialize an empty HashMap<String, Integer> to store candidate totals
 * 3. Iterate through both arrays simultaneously using the same index
 * 4. For each candidate at index i:
 *    - Get current candidate name and their votes
 *    - Update map: add votes to existing total, or initialize with votes
 *    - Use getOrDefault() to handle both cases cleanly
 * 5. Return the populated map with summed votes for each candidate
 *
 * COMPLEXITY ANALYSIS:
 * - Time Complexity: O(n), where n = length of the arrays
 *   (Single pass through both arrays simultaneously)
 * - Space Complexity: O(k), where k = number of unique candidates
 *   (Map storage for each distinct candidate name)
 * - Best Case: O(1) for empty arrays or single candidate
 * - Worst Case: O(n) when all candidates are unique
 * - Assumes array length n >= number of unique candidates k
 */
public class group4 {

    /**
     * Aggregates votes for each candidate from parallel arrays
     * @param names Array of candidate names
     * @param votes Array of vote counts corresponding to names
     * @return Map of candidate name to total vote sum
     * @throws IllegalArgumentException if arrays have different lengths
     */
    public static Map<String, Integer> aggregateVotes(String[] names, int[] votes) {
        // Validate input arrays have same length
        if (names == null || votes == null) {
            throw new IllegalArgumentException("Arrays cannot be null");
        }
        if (names.length != votes.length) {
            throw new IllegalArgumentException(
                    "Arrays must have same length: names=" + names.length +
                            ", votes=" + votes.length
            );
        }

        Map<String, Integer> voteTotals = new HashMap<>();

        // Iterate through parallel arrays and aggregate votes
        for (int i = 0; i < names.length; i++) {
            String candidate = names[i];
            int voteCount = votes[i];

            // Add votes to existing total, or initialize with current votes
            voteTotals.put(candidate, voteTotals.getOrDefault(candidate, 0) + voteCount);
        }

        return voteTotals;
    }

    // Example usage and testing
    public static void main(String[] args) {
        // Test case from problem statement
        String[] names = {"mohamed", "ahmed", "mohamed"};
        int[] votes = {1, 2, 3};

        Map<String, Integer> result = aggregateVotes(names, votes);

        System.out.println("Candidates: " + java.util.Arrays.toString(names));
        System.out.println("Votes: " + java.util.Arrays.toString(votes));
        System.out.println("Aggregated Results:");

        // Print each candidate and their total
        for (Map.Entry<String, Integer> entry : result.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }

        // Additional test cases
        System.out.println("\n--- Additional Tests ---");

        // Test with more duplicates
        String[] names2 = {"alice", "bob", "alice", "bob", "alice"};
        int[] votes2 = {5, 3, 2, 7, 1};
        System.out.println("\nTest 2: " + aggregateVotes(names2, votes2));
        // {alice=8, bob=10}

        // Test with all unique candidates
        String[] names3 = {"a", "b", "c"};
        int[] votes3 = {10, 20, 30};
        System.out.println("Test 3: " + aggregateVotes(names3, votes3));
        // {a=10, b=20, c=30}

        // Test empty arrays
        String[] names4 = {};
        int[] votes4 = {};
        System.out.println("Test 4 (empty): " + aggregateVotes(names4, votes4));
        // {}
    }
}
