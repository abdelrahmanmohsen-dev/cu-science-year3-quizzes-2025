import java.util.Arrays;

public class Group_3 {

    /**
     * Problem:
     * Given two arrays of integers, write an algorithm that returns
     * all numbers from the first array that are NOT present in the second array.
     *
     * Example:
     * arr1 = [1, 2, 3, 4], arr2 = [2, 4]
     * Output → [1, 3]
     */

    public static int[] DNE(int[] arr1, int[] arr2) {
        // Handle null or empty arrays
        if (arr1 == null || arr2 == null) {
            throw new IllegalArgumentException("Input arrays cannot be null");
        }
        if (arr1.length == 0) return new int[0];

        // Create a copy of arr1 to work with
        int[] copy = Arrays.copyOf(arr1, arr1.length);
        int count = 0;

        // For each element in arr1, check if it exists in arr2
        for (int i = 0; i < copy.length; i++) {
            boolean exists = false;

            for (int j = 0; j < arr2.length; j++) {
                if (copy[i] == arr2[j]) {
                    exists = true;
                    break;
                }
            }

            // If the element doesn't exist in arr2, keep it
            if (!exists) {
                copy[count++] = copy[i];
            }
        }

        // Return only the part of the array that contains valid results
        return Arrays.copyOfRange(copy, 0, count);
    }

    /** Helper method to print results */
    public static void printArray(String testName, int[] arr) {
        System.out.println(testName + " → " + Arrays.toString(arr));
    }

    /** Test the function */
    public static void main(String[] args) {
        System.out.println("=== Testing DNE (Does Not Exist) Function ===");

        // Test 1: Some numbers don't exist in second array
        printArray("Test 1", DNE(new int[]{1, 2, 3, 4}, new int[]{2, 4}));

        // Test 2: All numbers exist in the second array → should return empty
        printArray("Test 2", DNE(new int[]{5, 6, 7}, new int[]{5, 6, 7}));

        // Test 3: No overlap between arrays → should return all from arr1
        printArray("Test 3", DNE(new int[]{10, 20, 30}, new int[]{1, 2, 3}));

        // Test 4: arr2 is empty → should return all arr1
        printArray("Test 4", DNE(new int[]{8, 9, 10}, new int[]{}));

        // Test 5: arr1 is empty → should return empty array
        printArray("Test 5", DNE(new int[]{}, new int[]{1, 2, 3}));
    }
}
