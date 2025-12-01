package Chapter4;

import java.util.HashMap;
import java.util.Map;

/**
 * Solves the Coin Change problem using a Greedy approach.
 * <p>
 * Note:
 * The greedy method does NOT always produce the optimal (minimum-coin)
 * solution for every set of coin denominations.
 * <p>
 * However, it IS guaranteed to be optimal for certain structured coin systems.
 * One important class is when:
 *   - c₁ = 1
 *   - each coin is a multiple of the previous one
 *          ( cᵢ = cᵢ₋₁ * k) : 1 < i <= n && k ∈ ℕ
 *
 * <p>
 * Such sets form a geometric progression (like {1, 7, 28}) and the greedy
 * algorithm always yields the optimal solution for them.
 */

public class CoinProblem {

    /**
     * Calculates the coins needed for a specific amount.
     * @param amount The target monetary value.
     * @param coins An array of available coin denominations (Must be sorted in ascending order).
     *              OR you can sort it using the sort builtin function.
     * @return A Map where Key = Coin Denomination, Value = Count of that coin.
     */
    public static Map<Integer, Integer> Change(int amount, int[] coins) {
        Map<Integer, Integer> coinCount = new HashMap<>();

        // --- Selection Procedure Initialization ---
        // Start with the largest denomination available (last element of sorted array)
        int idx = coins.length - 1;
        int coin = coins[idx];

        // --- Solution Check Loop ---
        // Continue as long as we still have an amount to cover
        while (amount > 0) {

            // --- Feasibility Check ---
            // Check if the current coin can be part of the solution (coin value <= remaining amount)
            if (amount / coin >= 1) {
                // Calculate how many of this coin we can use (e.g., 9 / 5 = 1)
                int times = amount / coin;

                // Add to our solution set
                coinCount.put(coin, times);

                // Update the remaining amount
                amount -= times * coin;
            }

            // Move to the next smaller coin for the next iteration
            // This is the greedy step: try the largest, then the next largest, etc.
            if (idx - 1 >= 0) {
                coin = coins[--idx];
            } else {
                // Break if we run out of coin types (safety check)
                break;
            }
        }
        return coinCount;
    }

    public static void main(String[] args) {
        int change = 9;
        // Coins must be sorted for this logic to work
        int[] coins = {1, 5, 10};

        Map<Integer, Integer> map = Change(change, coins);
        System.out.println(map); // Expected: {5=1, 1=4} for greedy, though optimal is {5=1, 1=4}.
        // Wait, for 9: Greedy tries 10 (fail), tries 5 (takes 1, rem 4), tries 1 (takes 4).
    }
}