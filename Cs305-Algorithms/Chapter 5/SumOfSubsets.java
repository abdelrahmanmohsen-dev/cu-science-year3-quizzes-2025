public class SumOfSubsets {
    // Controls printing commas between subsets
    static boolean firstSubset = true;

    /**
     * Recursive backtracking function to find all subsets
     * whose sum equals the target weight.
     *
     * @param i       current index in the set
     * @param weight  current sum of the selected elements
     * @param total   sum of the remaining elements
     * @param w       array of weights (1-based indexing)
     * @param targetW required target sum
     * @param include boolean array to track included elements
     */
    public static void sumOfSubsets(int i, int weight, int total,
                                    int[] w, int targetW, Boolean[] include) {

        // Check whether the current node is promising
        if (promising(i, weight, total, w, targetW)) {

            // If the current sum equals the target, print the solution
            if (weight == targetW) {

                // Comma between subsets
                if (!firstSubset) {
                    System.out.print(", ");
                }
                firstSubset = false;

                // Print one subset
                System.out.print("{ ");
                boolean firstElement = true;

                for (int j = 1; j <= i; j++) {
                    if (include[j]) {

                        // Comma between elements inside the subset
                        if (!firstElement) {
                            System.out.print(", ");
                        }
                        firstElement = false;

                        System.out.print(w[j]);
                    }
                }
                System.out.print(" }");
            } else {

                // Include the next element in the subset
                include[i + 1] = true;
                sumOfSubsets(i + 1, weight + w[i + 1], total - w[i + 1], w, targetW, include);

                // Exclude the next element from the subset
                include[i + 1] = false;
                sumOfSubsets(i + 1, weight, total - w[i + 1], w, targetW, include);

            }
        }
    }


    static boolean promising(int i, int weight, int total, int[] w, int targetW) {

        // Check:
        // 1) Current weight + remaining total can still reach target
        // 2) Either we already reached the target
        //    OR adding the next element does not exceed the target
        return (weight + total >= targetW)
                && ((weight == targetW) || (weight + w[i + 1] <= targetW));
    }

    /* =========================
      TEST CASE
      ========================= */
    public static void main(String[] args) {

        int[] w = {0, 1, 2, 3, 4, 5}; // 1-based indexing
        int target = 6;
        Boolean[] include = new Boolean[w.length];
        int total = 1 + 2 + 3 + 4 + 5;

        // Start printing the main set
        System.out.print("All subsets = { ");

        sumOfSubsets(0, 0, total, w, target, include);

        // Close the main set
        System.out.println(" }");
    }
}
