package Chapter_3;

import java.util.Arrays;

/**
 * Implements the Floyd-Warshall algorithm using static methods.
 * This version calculates only the all-pairs shortest path distance matrix (D).
 * @version 1.0
 */
public class floyed_v1 {

    /**
     * Executes the Floyd-Warshall algorithm.
     *
     * @param W The input adjacency matrix (W) representing the graph's weights.
     * 'inf' should be used for non-adjacent vertices.
     * @return A 2D array (D) containing the shortest path distances between all pairs.
     */
    public static int[][] floyed1(int[][] W) {
        int n_vertices = W.length;
        int[][] D = new int[n_vertices][n_vertices];

        copy(W, D);

        for (int k = 0; k < n_vertices; k++) {
            for (int i = 0; i < n_vertices; i++) {
                for (int j = 0; j < n_vertices; j++) {
                    // Note: This relies on using a "safe" infinity to prevent overflow
                    D[i][j] = Math.min(D[i][j], D[i][k] + D[k][j]);
                }
            }
        }
        return D;
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
     * Main method for testing the floyed1 algorithm.
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

        int[][] D = floyed1(W);

        // printing the D matrix
        System.out.println("the D matrix is :");
        for (int[] ints : D) {
            System.out.println(Arrays.toString(ints));
        }
        /* Expected : [[0, 1, 3, 1, 4],
         *             [8, 0, 3, 2, 5],
         *             [10, 11, 0, 4, 7],
         *             [6, 7, 2, 0, 3],
         *             [3, 4, 6, 4, 0]]
         */
    }
}