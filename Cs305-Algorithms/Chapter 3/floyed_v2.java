package Chapter3;

import java.util.Arrays;
import java.util.List;

/**
 * Implements the Floyd-Warshall algorithm using static methods.
 * This version calculates both the distance matrix (D) and the
 * predecessor matrix (P) for path reconstruction.
 * @version 2.0
 */
public class floyed_v2 {

    /**
     * Executes the Floyd-Warshall algorithm to find all-pairs shortest paths
     * and their corresponding predecessor matrix.
     *
     * @param W The input adjacency matrix (W).
     * @return A List containing two matrices:
     * - Index 0: The shortest path distance matrix (D).
     * - Index 1: The predecessor matrix (P).
     */
    public static List<int[][]> floyed2(int[][] W) {
        int n_vertices = W.length;

        int[][] D = new int[n_vertices][n_vertices];
        int[][] P = new int[n_vertices][n_vertices];

        copy(W, D);

        for (int k = 0; k < n_vertices; k++) {
            for (int i = 0; i < n_vertices; i++) {
                for (int j = 0; j < n_vertices; j++) {
                    // Check for a shorter path and update P if one is found
                    if (D[i][j] > D[i][k] + D[k][j]) {
                        P[i][j] = k + 1; // Store 1-based index of intermediate vertex
                        D[i][j] = D[i][k] + D[k][j];
                    }
                }
            }
        }

        return List.of(D, P);
    }

    /**
     * A utility method to deep copy the contents of one matrix to another.
     *
     * @param W The source matrix (initial weights).
     * @param D The destination matrix (to be computed).
     */
    public static void copy(int[][] W, int[][] D) {
        int n  = W.length;

        for (int i = 0; i < n; i++) {
            System.arraycopy(W[i], 0, D[i], 0, n);
        }
    }

    /**
     * Prints the shortest path from vertex q to vertex r.
     * Note: This method re-computes the algorithm every time it is called.
     *
     * @param q The starting vertex (1-based index).
     * @param r The ending vertex (1-based index).
     * @param W The graph's weight matrix.
     */
    public static void path(int q, int r, int[][] W) {
        int[][] P = floyed2(W).getLast();

        System.out.print("[v" + q);
        helperPath(q, r, P);
        System.out.println(", v" + r + "]");
    }

    /**
     * A private recursive helper method to reconstruct the path.
     *
     * @param q The current starting vertex (1-based).
     * @param r The current ending vertex (1-based).
     * @param P The predecessor matrix.
     */
    private static void helperPath(int q, int r, int[][] P) {
        // Adjust for 0-based indexing in the matrix
        q = q - 1;
        r = r - 1;

        if (P[q][r] != 0) {
            // Recurse on the path from q to the intermediate vertex
            helperPath(q + 1, P[q][r], P);
            // Print the intermediate vertex
            System.out.print(", v" + P[q][r]);
            // Recurse on the path from the intermediate vertex to r
            helperPath(P[q][r], r + 1, P);
        }
    }

    /**
     * Main method for testing the floyed2 algorithm and path reconstruction.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // the representation of infinity and it's divided by 2 to avoid integer overflow
        int inf = Integer.MAX_VALUE / 2;
        int[][] W = {{0, 1, inf, 1, 5},
                {9, 0, 3, 2, inf},
                {inf, inf, 0, 4, inf},
                {inf, inf, 2, 0, 3},
                {3, inf, inf, inf, 0}};

        int[][] D = floyed2(W).getFirst();
        int[][] P = floyed2(W).getLast();

        // printing the D matrix
        for(int[] nums : D) {
            System.out.println(Arrays.toString(nums));
        }
        /* Expected : [[0, 1, 3, 1, 4],
        *             [8, 0, 3, 2, 5],
        *             [10, 11, 0, 4, 7],
        *             [6, 7, 2, 0, 3],
        *             [3, 4, 6, 4, 0]]
        */

        System.out.println("============================");

        // printing the P matrix
        for(int[] nums : P) {
            System.out.println(Arrays.toString(nums));
        }
        /* Expected: [[0, 0, 4, 0, 4]
        *            [5, 0, 0, 0, 4]
        *            [5, 5, 0, 0, 4]
        *            [5, 5, 0, 0, 0]
        *            [0, 1, 4, 1, 0]
        */


        System.out.println("===========================");

        // testing the path form v2 -> v1
        path(2 , 1, W);
    }
}