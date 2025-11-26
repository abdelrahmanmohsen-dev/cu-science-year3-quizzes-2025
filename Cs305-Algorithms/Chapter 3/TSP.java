package Chapter3;

/**
 * Solves the Traveling Salesperson Problem (TSP) using Dynamic Programming.
 * * <p>
 * This implementation finds the shortest Hamiltonian cycle in a weighted graph.
 * It supports asymmetric graphs and checks for impossible paths (disconnected graphs).
 * </p>
 * * <h3>Complexity:</h3>
 * <ul>
 * <li><b>Time Complexity:</b> O(n^2 * 2^n) - We iterate through all subsets and all pairs of nodes.</li>
 * <li><b>Space Complexity:</b> O(n * 2^n) - To store the DP table (D) and Parent table (P).</li>
 * </ul>
 */
public class TSP {

    /**
     * Executes the TSP algorithm to find the minimum tour cost.
     * @param n          The number of cities.
     * @param W          The Adjacency Matrix (Weight Matrix), where W[i][j] is the cost from i to j.
     * @param P          The Parent table used to reconstruct the path (output).
     * @param minLength  An array of size 1 to store the resulting minimum path length (output).
     */
    public static void travel(int n, int[][] W, int[][] P, int[] minLength) {

        // Represents the set of all cities excluding the start node (City 0).
        // Since City 0 is fixed as start/end, we only need n-1 bits.
        // Example: If n=4, FULL = 111 (binary) = 7.
        int FULL = (1 << (n - 1)) - 1;

        // D[i][subset]: The minimum cost to visit the set of cities represented by 'subset',
        // ending at city 'i'.
        int[][] D = new int[n][1 << (n - 1)];

        // -----------------------------------------------------------------
        // 1. Base Cases
        // -----------------------------------------------------------------
        // Initialize the cost to travel from City 0 directly to City i.
        // (In this DP formulation, this represents a path containing only {i}).
        for (int i = 1; i < n; i++) {
            D[i][0] = W[i][0];
        }

        // -----------------------------------------------------------------
        // 2. Fill DP Table (Iterate by subset size 'k')
        // -----------------------------------------------------------------
        // We build up solutions from small subsets (size 1) to large subsets (size n-2).
        for (int k = 1; k <= n - 2; k++) {

            // Iterate through every possible subset mask
            for (int subset = 0; subset <= FULL; subset++) {

                // Only process subsets of size 'k'
                if (Integer.bitCount(subset) != k) continue;

                // Iterate through every possible 'next' city i (from 1 to n-1)
                for (int i = 1; i < n; i++) {

                    // Optimization: The 'next' city i cannot be part of the already visited subset.
                    // We check if the bit corresponding to i (shifted by i-1) is set.
                    if ((subset & (1 << (i - 1))) != 0) continue;

                    int best = Integer.MAX_VALUE / 4; // Use a safe infinity to avoid overflow
                    int parent = -1;

                    // -------------------------------------------------------------
                    // Transition: Try coming to 'i' from every city 'j' in the subset
                    // -------------------------------------------------------------
                    for (int j = 1; j < n; j++) {
                        int bit = 1 << (j - 1);

                        // If city j is not in the current subset, it can't be the previous node
                        if ((subset & bit) == 0) continue;

                        // The state before visiting j was (subset without j)
                        int prev = subset ^ bit;

                        //Cost = Cost(Start -> ... -> j) + Cost(j -> i)
                        int cost = W[i][j] + D[j][prev];

                        if (cost < best) {
                            best = cost;
                            parent = j;
                        }
                    }

                    // Store the optimal cost and the parent pointer for this state
                    D[i][subset] = best;
                    P[i][subset] = parent;
                }
            }
        }

        // -----------------------------------------------------------------
        // 3. Final Step (Connect back to Start Node 0)
        // -----------------------------------------------------------------
        // We now have costs for paths visiting all cities and ending at j.
        // We must add the final leg: j -> 0.
        int overallBest = Integer.MAX_VALUE;
        int last = -1;

        for (int j = 1; j < n; j++) {
            int bit = 1 << (j - 1);
            int subset = FULL ^ bit; // The subset containing everything EXCEPT j

            // Total Cost = Path(Start -> ... -> j) + Cost(0 -> j) *Wait, matrix implies W[0][j]*
            // Note: Depending on W's directionality, verify if this is 0->j or j->0.
            // Based on base case D[i][0] = W[i][0], this reconstructs backwards.
            int cost = W[0][j] + D[j][subset];

            if (cost < overallBest) {
                overallBest = cost;
                last = j;
            }
        }

        // Save the result
        P[0][FULL] = last; // Store the last node before returning to 0
        minLength[0] = overallBest;
    }

    /**
     * Reconstructs and prints the optimal path based on the Parent (P) matrix.
     * @param n The number of cities.
     * @param P The populated Parent matrix from the travel method.
     */
    public static void printPath(int n, int[][] P) {
        int FULL = (1 << (n - 1)) - 1;

        System.out.print("Optimal Path: 1 -> "); // Outputting 1-based index for readability

        int current = P[0][FULL];
        int subset = FULL ^ (1 << (current - 1)); // Remove the current node from the mask

        // Backtrack through the parent pointers
        while (current != -1) {
            System.out.print((current + 1) + " -> ");

            int next = P[current][subset];

            // Avoid errors on the last step
            if (next != -1) {
                subset = subset ^ (1 << (next - 1)); // Remove next node from mask
            }

            current = next;

            if (current == 0 || current == -1) break;
        }

        System.out.println("1"); // Complete the cycle back to start
    }

    public static void main(String[] args) {
        // Test Case Setup: 4 Cities, Asymmetric, Non-Complete Graph
        int n = 4;
        int INF = Integer.MAX_VALUE / 4; // Safe infinity to prevent overflow during addition

        // Adjacency Matrix (W)
        // Note: W[i][j] is the cost to go from City i to City j
        int[][] W = {
                { 0,   2,  9,   INF},
                { 1,   0,  6,   4},
                { INF, 7,  0,   8},
                { 6,   3,  INF, 0}
        };

        // DP State containers
        int[][] P = new int[n][1 << (n - 1)];
        int[] minLength = new int[1];

        // Execute Algorithm
        travel(n, W, P, minLength);

        // Output Results
        System.out.println("Tour length = " + minLength[0]);
        printPath(n, P);
    }
}