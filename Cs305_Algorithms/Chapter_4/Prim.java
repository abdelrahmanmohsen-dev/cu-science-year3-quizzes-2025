package Chapter_4;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of Prim's Algorithm to find the Minimum Spanning Tree (MST).
 * The algorithm builds the MST node by node, always adding the cheapest edge
 * connecting a node in the MST to a node outside it.
 */
public class Prim {

    /**
     * Executes Prim's algorithm.
     * @param n The number of vertices in the graph.
     * @param W The adjacency matrix representing edge weights. W[i][j] is the weight between i and j.
     * @return A list of Edges constituting the Minimum Spanning Tree.
     */
    public static List<Edge> prim(int n, int[][] W) {

        int i, j, vnear = 0; // vnear: The vertex nearest to the current MST
        int[] nearest = new int[n]; // nearest[i]: The vertex in the MST that is closest to vertex i
        int[] distance = new int[n]; // distance[i]: The weight of the edge between i and nearest[i]

        List<Edge> F = new ArrayList<>(); // F: The set of edges in the MST

        // --- Initialization ---
        // Initialize distance and nearest arrays based on the starting node (node 0).
        // We start with node 0 in the MST.
        for (i = 1; i < n; i++) {
            nearest[i] = 0; // Initially, all nodes are closest to node 0
            distance[i] = W[0][i]; // Distance is the weight from node 0 to i
        }

        // --- Main Loop ---
        // Repeat n-1 times to add n-1 edges to the MST
        for (j = 1; j < n; j++) {

            int min = Integer.MAX_VALUE;

            // --- Selection Procedure ---
            // Find the vertex (vnear) not yet in the MST with the smallest distance to the MST.
            for (i = 1; i < n; i++) {
                // distance[i] >= 0 checks if the vertex is not yet in MST (we use -1 to mark included vertices)
                if (distance[i] >= 0 && distance[i] < min) {
                    min = distance[i];
                    vnear = i;
                }
            }

            // Create an edge object for the selected connection.
            // Note: Adding +1 to indices to convert 0-based index to 1-based label for display.
            Edge e = new Edge(vnear + 1, nearest[vnear] + 1, min);

            // Add the selected edge to the MST list
            F.add(e);

            // Mark the vertex 'vnear' as added to the MST by setting its distance to -1
            distance[vnear] = -1;

            // --- Update Distances ---
            // Update the distance and nearest arrays for the remaining vertices not in the MST.
            // We check if connecting to the newly added vertex (vnear) is shorter than the previous best connection.
            for (i = 1; i < n; i++) {
                if (W[i][vnear] < distance[i]) {
                    distance[i] = W[i][vnear]; // Update with cheaper weight
                    nearest[i]  = vnear;       // Update the nearest connection point
                }
            }
        }

        return F;
    }

    public static void main(String[] args) {
        // Infinite/Max value usually represents no edge, but here simple integers are used.
        // 0 on diagonal represents distance to self.
        int[][] W = {{0, 1, 2, 4},
                {1, 0, 3, 4},
                {2, 3, 0, 5},
                {4, 4, 5, 0}};

        // Execute Prim's algorithm for 4 nodes
        List<Edge> F = prim(4, W);

        // Output the result
        System.err.println(F);
    }
}