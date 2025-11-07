/**
 * Problem:
 * - write a recursive method to reverse a string
 * don't use any built-in functions or stringBuilder
 * don't use SUBSTRING
 * don't change the input string.
 * <p>
 * Time Complexity:
 * See individual method comments. Due to string concatenation
 * (which creates new strings), the complexities are not O(N).
 */
public class Group_4 {

    /**
     * Method 1 (Linear Recursion): Takes the character at the current index
     * and appends it to the recursive result of the rest of the string.
     * <p>
     * Time Complexity: O(N^2) - where N is the length of the string.
     * At each step, (e.g., "c" + "ba") creates a new string and copies
     * all characters. The work is N + (N-1) + ... + 1, which is O(N^2).
     *
     * @param str The string to reverse.
     * @param idx The current index (should be called with str.length() - 1).
     * @return The reversed string.
     */
    public static String reverse(String str, int idx) {
        // Base case: If we have gone past the first character
        if (idx == -1) return "";

        // Append the current char to the reversed result of the substring before it
        return str.charAt(idx) + reverse(str, idx - 1);
    }

    /**
     * Method 2 (Divide and Conquer): Splits the string into two halves,
     * recursively reverses each half, and then concatenates them
     * in reversed order (right + left).
     * <p>
     * Time Complexity: O(N log N) - where N is the length of the string.
     * The recurrence relation is T(N) = 2T(N/2) + O(N). It's O(N) at
     * each level because the concatenation (right + left) must create
     * a new string of length N. This is the same as Merge Sort.
     *
     * @param str   The string to reverse.
     * @param start The starting index of the current substring.
     * @param end   The ending index of the current substring.
     * @return The reversed substring.
     */
    public static String reverseDAC(String str, int start, int end) {
        // Base case: If the substring is a single character
        if (end == start) {
            return String.valueOf(str.charAt(start));
        }

        // Divide: Calculate the middle index
        int mid = start + (end - start) / 2;

        // Conquer: Recurse on the left half
        String left = reverseDAC(str, start, mid);

        // Conquer: Recurse on the right half
        String right = reverseDAC(str, mid + 1, end);

        // Combine: Concatenate the reversed halves in reverse order
        return right + left;
    }

    public static void main(String[] args) {
        // TestCase 1 (using DAC)
        String a = "Mohamed";
        System.out.println("DAC Reverse of : \"" + a + "\"");
        System.out.println("is: \"" + reverseDAC(a, 0, a.length() - 1) + "\"");
        // Expected: "demahoM"

        System.out.println("================================");

        // TestCase 2 (using Linear Recursion)
        String b = "Hello World";
        System.out.println("Linear Reverse of : \"" + b + "\"");
        System.out.println("is: \"" + reverse(b, b.length() - 1) + "\"");
        // Expected: "dlroW olleH"

        System.out.println("================================");

        // TestCase 3 (using DAC with even length)
        String c = "Java";
        System.out.println("DAC Reverse of : \"" + c + "\"");
        System.out.println("is: \"" + reverseDAC(c, 0, c.length() - 1) + "\"");
        // Expected: "avaJ"
    }
}