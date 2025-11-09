public class BinarySearch {
    public static void main(String[] args) {
        int[] S = {2, 4, 6, 8, 10, 12, 14}; // Array to search in
        int x = 10; // Element to find

        int result = binarySearch(0, S.length-1, x, S); // Call binary search function

        if (result != -1)
            System.out.println("Element found at index: " + result); // Element found
        else
            System.out.println("Element not found."); // Element not found
    }

    public static int binarySearch(int low, int high, int x, int[] ar) {
        int mid;
        if (low > high) {
            return -1; // Base case: element not found
        } else
            mid = (low + high) / 2; // Find middle index

        if (low == high) {
            return mid; // Base case: only one element left
        } else if (x < ar[mid]) {
            return binarySearch(low, mid - 1, x, ar); // Search left half
        } else {
            return binarySearch(mid + 1, high, x, ar); // Search right half
        }
    }
}
