/*
* before you start make sure that you have the Edge class
* */

package Chapter_4;

import SortingAlgorithms.MergeSort;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Implements Kruskal's Algorithm for finding the Minimum Spanning Tree (MST)
 * of a graph.
 * NOTE: I have implemented this algorithm using a List<List<Integer>>
 * which is not the best way in terms of time complexity
 * <p>
 * if you are interested on that you can try to implement it using an array with Path Compression and Union by Rank
 * would be significantly faster (nearly O(1) per operation).
 * instead of O(n^2) for my List way but good luck implementing that you will need it :)
 */
public class Kruskal {

    /**
     * Executes Kruskal's algorithm to find the Minimum Spanning Tree.
     * @param n The number of vertices (nodes) in the graph.
     * @param m The number of edges in the graph (not strictly needed by Kruskal's).
     * @param E The list of all edges in the graph.
     * @return A List of Edges that form the Minimum Spanning Tree (MST).
     */
    public static List<Edge> kruskal(int n, int m, List<Edge> E) {
        // Initialize edge index pointer
        int i = 0;

        // 1. Sort all edges in non-decreasing order of weight
        ArrayList<Edge> sorted_edges = sort(E);

        // F will store the edges of the resulting MST
        List<Edge> F = new ArrayList<>();

        // Subsets is the Disjoint Set Union (DSU) structure.
        // It's a List where each element is a List of vertices in a connected component.
        List<List<Integer>> subsets = new ArrayList<>();

        // 2. Initialization: Create 'n' disjoint sets.
        // Each vertex is initially in its own set (e.g., {{1}, {2}, {3}, ... {n}})
        for (int k = 1; k <= n; k++) {
            List<Integer> s = new ArrayList<>();
            s.add(k); // Add the vertex to its own new set
            subsets.add(s); // Add the new set to the master list of subsets
        }

        // 3. Loop through sorted edges until the MST is complete OR all edges are checked.
        // An MST has exactly n-1 edges.
        while (F.size() < n - 1 && i < sorted_edges.size()) {
            // Get the current edge (smallest available edge) and increment the index
            Edge curr = sorted_edges.get(i++);
            int a = curr.from;
            int b = curr.to;

            // Find the index (representative) of the subsets containing vertices 'a' and 'b'.
            int p = find(a, subsets);
            int q = find(b, subsets);

            // If the representatives are different (i.e., 'a' and 'b' are in different subsets),
            // then adding this edge will NOT create a cycle.
            if (p != q) {
                // Add the edge to the MST (F)
                merge(p, q, subsets);

                // Perform the Union operation: Merge the two subsets.
                F.add(curr);
            }
            // If p == q, the edge is discarded because it would form a cycle.
        }

        return F;
    }

    /**
     * Performs the Union operation: merges two subsets (components).
     * It merges the component at index 'q' into the component at index 'p'.
     * @param p The index of the first subset (destination).
     * @param q The index of the second subset (source, which is removed).
     * @param subsets The DSU structure (List of Lists).
     */
    private static void merge(int p, int q, List<List<Integer>> subsets) {
        // Get the list of vertices for the subset to be merged (subset at index q)
        List<Integer> subset2 = subsets.get(q);

        // Add all elements from the second subset (q) into the first subset (p)
        subsets.get(p).addAll(subset2);

        // Remove the redundant second subset (q) from the master list
        subsets.remove(q);
    }

    /**
     * Performs the Find operation: determines which subset a vertex 'x' belongs to.
     * This implementation searches linearly through all vertices in all subsets.
     * @param x The vertex to find the subset for.
     * @param subsets The DSU structure (List of Lists).
     * @return The index of the subset containing 'x', or -1 if 'x' is not found.
     */
    private static int find(int x, List<List<Integer>> subsets) {
        // Iterate through all component lists
        for (int i = 0; i < subsets.size(); i++) {
            List<Integer> subset = subsets.get(i);

            // Iterate through all vertices in the current component list
            for (Integer j : subset) {
                // If the vertex 'x' is found
                if (j == x)
                    return i; // Return the index of the list (the subset's representative index)
            }
        }
        return -1; // Should not happen if initialization is correct
    }

    /**
     * Sorts the list of edges using MergeSort.
     * @param E The unsorted list of edges.
     * @return A new ArrayList containing the edges sorted by weight.
     */
    private static ArrayList<Edge> sort(List<Edge> E) {
        // Convert the List to an array, which is required for the MergeSort implementation
        Edge[] edges = E.toArray(new Edge[0]);

        // Use the external MergeSort implementation to sort the array in place
        // NOTE: Requires the Edge class to implement Comparable (usually on weight)
        // Also this is a pre implemented generic class that I created on this repo
        // ON : SortingAlgorithms.MergeSort
        // you can use any other sorting way but that was the best for me
        MergeSort.sort(edges);

        // Convert the sorted array back into a new ArrayList and return it
        return new ArrayList<>(Arrays.asList(edges));
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = 7;
        int m = 10;
        List<Edge> E = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            System.out.print("Type the Edge in the format of (form to wight): ");
            int from = in.nextInt();
            int to = in.nextInt();
            int wight = in.nextInt();
            E.add(new Edge(from, to, wight));
        }

        List<Edge> E1 = kruskal(n, m, E);
        System.out.println(E1);
    }
}
