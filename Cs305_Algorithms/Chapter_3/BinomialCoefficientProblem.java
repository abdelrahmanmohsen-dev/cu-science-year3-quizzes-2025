package Chapter_3;

/**
 * Provides various methods to calculate the binomial coefficient,
 * also known as "n choose k" or C(n, k).
 */
public class BinomialCoefficientProblem {

    /**
     * Calculates C(n, k) using a pure recursive approach based on
     * Pascal's Identity: C(n, k) = C(n-1, k) + C(n-1, k-1).
     * <p>
     * <b>Warning:</b> This method is highly inefficient due to
     * overlapping subproblems and has an exponential time complexity (O(2^n)).
     *
     * @param n The total number of items.
     * @param k The number of items to choose.
     * @return The binomial coefficient C(n, k).
     */
    public static int RecursiveSolution(int n, int k) {
        if (k == 0 || n == k) return 1;

        return RecursiveSolution(n - 1, k) + RecursiveSolution(n - 1, k - 1);
    }

    /**
     * Calculates C(n, k) using a bottom-up dynamic programming approach
     * with a 2D array (tabulation).
     * <p>
     * This method builds Pascal's triangle in a table.
     * Time Complexity: O(n*k)
     * Space Complexity: O(n*k)
     *
     * @param n The total number of items.
     * @param k The number of items to choose.
     * @return The binomial coefficient C(n, k).
     */
    public static int DPSolution(int n, int k) {
        int[][] dp = new int[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            // Iterate up to min(i, k) because C(n, k) is 0 if k > n
            for (int j = 0; j <= Math.min(i, k); j++) {
                if (j == 0 || j == i) dp[i][j] = 1;
                else dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }
        return dp[n][k];
    }

    /**
     * Calculates C(n, k) using a space-optimized dynamic programming
     * approach with a 1D array.
     * <p>
     * This method applies two optimizations:
     * 1. Uses the symmetry identity C(n, k) = C(n, n-k) to use less space.
     * 2. Uses only a 1D array of size O(k) to store the previous row.
     * <p>
     * Time Complexity: O(n * min(k, n-k))
     * Space Complexity: O(min(k, n-k))
     *
     * @param n The total number of items.
     * @param k The number of items to choose.
     * @return The binomial coefficient C(n, k).
     */
    public static int DPSolution2(int n, int k) {
        // Apply symmetry optimization
        if (k > n - k)
            k = n - k;

        int[] dp = new int[k + 1];
        // dp[0] is C(i, 0) which is always 1
        // We can set it once, but the loop handles it (if j==0)

        for (int i = 0; i <= n; i++) {
            // Loop backward to avoid overwriting values from the
            // previous iteration (row i-1) that are needed.
            for (int j = Math.min(i, k); j >= 0; j--) {
                if (j == 0 || j == i) dp[j] = 1;
                else dp[j] = dp[j - 1] + dp[j];
            }
        }
        return dp[k];
    }

    /**
     * Main method for testing the binomial coefficient solutions.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // TestCase1: Using the Recursive solution
        System.out.println("6C3 is equal to: " + RecursiveSolution(6, 3));
        // Expected: 6C3 = 20

        System.out.println("=====================");

        // TestCase2: Using the Optimized DP solution
        //            this test case will Apply symmetry optimization
        System.out.println("9C7 is equal to: " + DPSolution2(9, 7));
        // Expected: 9C2 = 36

        System.out.println("=====================");

        // TestCase3: Using the DP solution
        System.out.println("10C5 is equal to: " + DPSolution(10, 5));
        // Expected: 10C5 = 252
    }
}