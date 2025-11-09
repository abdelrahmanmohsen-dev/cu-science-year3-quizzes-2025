package Chapter3;

import java.util.Arrays;

/**
 * An object-oriented implementation of the Floyd-Warshall algorithm.
 * This class encapsulates the distance (D) and predecessor (P) matrices
 * as state, allowing path reconstruction to be called multiple times
 * without re-computing the algorithm.
 *
 * @version 3.0
 */
public class FloyedAlgorithm {
    /** The shortest path distance matrix. */
    private final int[][] D;
    /** The predecessor matrix for path reconstruction. */
    private final int[][] P;
    /** The number of vertices in the graph. */
    private final int n_vertices;


    /**
     * Constructs a new FloyedAlgorithm object and immediately runs
     * the algorithm to populate the D and P matrices.
     *
     * @param W The input adjacency matrix (W).
     */
    public FloyedAlgorithm(int[][] W) {
        this.n_vertices = W.length;

        D = new int[n_vertices][n_vertices];
        P = new int[n_vertices][n_vertices];

        // Copy initial weights to the distance matrix
        for (int i = 0; i < n_vertices; i++) {
            System.arraycopy(W[i], 0, D[i], 0, n_vertices);
        }

        // Run the algorithm to populate D and P
        floyed2();
    }

    /**
     * A private implementation of Floyd-Warshall that only calculates distances.
     * (Note: This method is not called by the constructor).
     */
    private void floyed1() {
        for (int k = 0; k < n_vertices; k++) {
            for (int i = 0; i < n_vertices; i++) {
                for (int j = 0; j < n_vertices; j++) {
                    D[i][j] = Math.min(D[i][j], D[i][k] + D[k][j]);
                }
            }
        }
    }

    /**
     * A private implementation of Floyd-Warshall that calculates both
     * the distance matrix (D) and the predecessor matrix (P).
     */
    private void floyed2() {
        for (int k = 0; k < n_vertices; k++) {
            for (int i = 0; i < n_vertices; i++) {
                for (int j = 0; j < n_vertices; j++) {
                    if (D[i][j] > D[i][k] + D[k][j]) {
                        P[i][j] = k + 1; // Store 1-based index
                        D[i][j] = D[i][k] + D[k][j];
                    }
                }
            }
        }

    }

    /**
     * Prints the shortest path from vertex q to vertex r.
     * Assumes vertices are 1-based.
     *
     * @param q The starting vertex (1-based index).
     * @param r The ending vertex (1-based index).
     */
    public void path(int q, int r) {
        System.out.print("[v" + q);
        helperPath(q, r);
        System.out.println(", v" + r + "]");
    }

    /**
     * A private recursive helper for the path reconstruction.
     *
     * @param q The current starting vertex (1-based).
     * @param r The current ending vertex (1-based).
     */
    private void helperPath(int q, int r) {
        // Adjust for 0-based indexing in the matrix
        q = q - 1;
        r = r - 1;

        if (P[q][r] != 0) {
            // Recurse on the first sub-path
            helperPath(q + 1, P[q][r]);
            // Print the intermediate vertex
            System.out.print(", v" + P[q][r]);
            // Recurse on the second sub-path
            helperPath(P[q][r], r + 1);
        }
    }

    /**
     * Gets the computed shortest path distance matrix.
     *
     * @return The 2D distance matrix (D).
     */
    public int[][] getD() {
        return D;
    }

    /**
     * Gets the computed predecessor matrix.
     *
     * @return The 2D predecessor matrix (P).
     */
    public int[][] getP() {
        return P;
    }
}

/**
 * A simple test class to drive the FloyedAlgorithm.
 */
class Test {
    /**
     * Main method for testing the FloyedAlgorithm class.
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

        FloyedAlgorithm f = new FloyedAlgorithm(W);

        // printing the path from v2 -> v1
        System.out.print("the path from v2 -> v1 is :");
        f.path(2, 1);
        // Expected: [v2, v4, v5, v1]

        // printing the path form v3 -> v2
        System.out.print("the path from v3 -> v2 is :");
        f.path(3, 2);
        // Expected: [v3, v4, v5, v1, v2]


        System.out.println("===================================");

        int[][] D = f.getD();
        int[][] P = f.getP();

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

        System.out.println("===================================");

        // printing the P matrix
        System.out.println("the P matrix is :");
        for (int[] ints : P) {
            System.out.println(Arrays.toString(ints));
        }
        /* Expected: [[0, 0, 4, 0, 4]
         *            [5, 0, 0, 0, 4]
         *            [5, 5, 0, 0, 4]
         *            [5, 5, 0, 0, 0]
         *            [0, 1, 4, 1, 0]
         */

    }
}