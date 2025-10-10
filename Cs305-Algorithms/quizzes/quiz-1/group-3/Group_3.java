import java.util.*;

/**
 * ===============================================================
 * Problem:
 * Given two arrays of integers, write an algorithm that returns
 * all numbers from the first array that are NOT present in the second array.
 * ===============================================================
 * Example:
 * arr1 = [1, 2, 3, 4], arr2 = [2, 4]
 * Output → [1, 3]
 */
public class Group_3 {

    // ---------------------------------------------------------------
    // Method 1: Brute Force Approach (O(n × m))
    // ---------------------------------------------------------------
    public static int[] DNE(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null) {
            throw new IllegalArgumentException("Input arrays cannot be null");
        }
        if (arr1.length == 0) return new int[0];

        int[] copy = Arrays.copyOf(arr1, arr1.length);
        int count = 0;

        for (int i = 0; i < copy.length; i++) {
            boolean exists = false;
            for (int j = 0; j < arr2.length; j++) {
                if (copy[i] == arr2[j]) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                copy[count++] = copy[i];
            }
        }

        return Arrays.copyOfRange(copy, 0, count);
    }

    // ---------------------------------------------------------------
    // Method 2: Optimized using HashSet (O(n + m))
    // ---------------------------------------------------------------
    public static int[] DNE_Optimized(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null) {
            throw new IllegalArgumentException("Input arrays cannot be null");
        }
        if (arr1.length == 0) return new int[0];

        HashSet<Integer> set2 = new HashSet<>();
        for (int num : arr2) {
            set2.add(num);
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (int num : arr1) {
            if (!set2.contains(num)) {
                result.add(num);
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    // ---------------------------------------------------------------
    // Method 3: Alternative Approach (FindCompliment)
    // ---------------------------------------------------------------
    /*
     *  Problem:
     *        Given two arrays of integers, write an algorithm that returns
     *        the numbers of the first array that are not in the second array.
     *
     *  Solving Method:
     *        1) Use a HashSet to contain the elements of the second array for O(1) lookup.
     *        2) Loop through the first array and check if each integer is in the second.
     *        3) Add integers not in the second array to an ArrayList and return it.
     *
     *  Time Complexity:
     *        O(MAX(N, M)) where N = size of the first array, M = size of the second array.
     *        Simplified: O(N)
     */
    public static ArrayList<Integer> FindCompliment(int[] arr1, int[] arr2) {
        HashSet<Integer> arr2set = new HashSet<>();

        for (int i : arr2) {
            arr2set.add(i);
        }

        ArrayList<Integer> ans = new ArrayList<>();
        for (int i : arr1) {
            if (!arr2set.contains(i)) {
                ans.add(i);
            }
        }

        return ans;
    }

    // ---------------------------------------------------------------
    // Helper Method for printing test results
    // ---------------------------------------------------------------
    public static void printArray(String testName, int[] arr) {
        System.out.println(testName + " → " + Arrays.toString(arr));
    }

    // ---------------------------------------------------------------
    // Test Cases
    // ---------------------------------------------------------------
    public static void main(String[] args) {
        System.out.println("=== Testing DNE (Does Not Exist) Functions ===");

        // ---- Method 1 Tests ----
        System.out.println("\n--- Brute Force Version ---");
        printArray("Test 1", DNE(new int[]{1, 2, 3, 4}, new int[]{2, 4}));
        printArray("Test 2", DNE(new int[]{5, 6, 7}, new int[]{5, 6, 7}));
        printArray("Test 3", DNE(new int[]{10, 20, 30}, new int[]{1, 2, 3}));
        printArray("Test 4", DNE(new int[]{8, 9, 10}, new int[]{}));
        printArray("Test 5", DNE(new int[]{}, new int[]{1, 2, 3}));

        // ---- Method 2 Tests ----
        System.out.println("\n--- Optimized HashSet Version ---");
        printArray("Test 1", DNE_Optimized(new int[]{1, 2, 3, 4}, new int[]{2, 4}));
        printArray("Test 2", DNE_Optimized(new int[]{5, 6, 7}, new int[]{5, 6, 7}));
        printArray("Test 3", DNE_Optimized(new int[]{10, 20, 30}, new int[]{1, 2, 3}));
        printArray("Test 4", DNE_Optimized(new int[]{8, 9, 10}, new int[]{}));
        printArray("Test 5", DNE_Optimized(new int[]{}, new int[]{1, 2, 3}));

        // ---- Method 3 Tests ----
        System.out.println("\n--- FindCompliment Version ---");
        System.out.println("Test1: " + FindCompliment(new int[]{1, 3, 5, 10}, new int[]{2, 3, 5, 9}));
        System.out.println("Test2: " + FindCompliment(new int[]{5, 3, 2, 1, 1}, new int[]{2, 3, 4, 9}));
        System.out.println("Test3: " + FindCompliment(new int[]{}, new int[]{1, 2, 3, 4}));
        System.out.println("Test4: " + FindCompliment(new int[]{54, 7, 9, 56}, new int[]{2, 3, 87, 4}));
        System.out.println("Test5: " + FindCompliment(new int[]{}, new int[]{}));
    }
}
