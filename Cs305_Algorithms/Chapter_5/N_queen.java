package Chapter_5;

public class N_queen {

    /**
     * The main recursive function to solve the N-Queens problem.
     * It tries to place a queen in the current row 'i' and then moves to the next row.
     *
     * @param i   The current row we are trying to place a queen in.
     * @param n   The total number of queens (and size of the board n x n).
     * @param col An array where col[i] represents the column number of the queen in row i.
     * Example: col[2] = 4 means the queen in row 2 is at column 4.
     */
    public static void queens(int i, int n, int[] col) {
        // 1. CHECK VALIDITY:
        // Before doing anything, check if the queen placed in the PREVIOUS row (passed in as i)
        // is valid (promising). If not, we stop this branch (Backtrack).
        if (promising(i, col)) {

            // 2. BASE CASE:
            // If 'i' equals 'n', it means we have successfully placed queens in all rows (1 to n).
            // We found a valid solution!
            if (i == n) {
                // Print the solution found
                for (int k = 1; k < n; k++) {
                    System.out.print(col[k] + "->");
                }
                System.out.println(col[n]); // Print the last one with a newline
            }
            else {
                // 3. RECURSIVE STEP:
                // If we haven't finished yet, try to place a queen in the NEXT row (i + 1).
                // We try every column 'j' from 1 to n for this next row.
                for (int j = 1; j <= n; j++) {
                    col[i + 1] = j;       // Place queen in next row at column j
                    queens(i + 1, n, col); // Recursively call for the next row
                }
            }
        }
    }

    /**
     * Checks if the queen placed at row 'i' is safe.
     * A position is safe if no other queen attacks it (same column or same diagonal).
     *
     * @param i   The row of the queen we are currently checking.
     * @param col The array storing the positions of all queens placed so far.
     * @return true if safe, false if under attack.
     */
    public static boolean promising(int i, int[] col) {
        int k = 1;
        boolean Switch = true; // Flag to keep checking. True means "still safe so far".

        // Check against every previous queen (from row 1 up to i-1)
        while (k < i && Switch) {

            // CONFLICT CHECK:
            // 1. col[i] == col[k]: Checks if they are in the same COLUMN.
            // 2. Math.abs(col[i] - col[k]) == i - k: Checks if they are on the same DIAGONAL.
            //    (In a diagonal, the absolute difference in columns equals the difference in rows)
            if (col[i] == col[k] || Math.abs(col[i] - col[k]) == i - k) {
                Switch = false; // Conflict found! Not promising.
            }
            k++;
        }
        return Switch;
    }

    public static void main(String[] args) {
        int n = 8; // Size of the board (8x8) and number of queens

        // Start the recursion from row 0.
        // We use size n + 1 for the array because we use 1-based indexing (rows 1 to n).
        // col[0] is unused dummy data.
        queens(0, n, new int[n + 1]);
    }
}