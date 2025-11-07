/**
 * Problem:
 * - Reverse the digits of an integer without using strings.
 * <p>
 * Time Complexity (Both Methods):
 * O(N) - where N is the number of digits in the integer.
 * Both solutions must process each digit of the number exactly once.
 */
public class Group_2 {

    /**
     * Method 1 (Iterative): Uses a while-loop to build the reversed number.
     * <p>
     * In each iteration, it "pops" the last digit off the original number
     * (using {@code num % 10}) and "pushes" it onto the end of the
     * {@code reversedNum} (by multiplying by 10 and adding).
     *
     * @param num The number to reverse.
     * @return The reversed number.
     */
    public static int ReversedNumIteratively(int num) {
        int reversedNum = 0;

        // Loop until all digits have been processed
        while (num != 0) {
            // 1. Multiply reversedNum by 10 to shift its digits left
            // 2. Get the last digit of num using modulo 10
            // 3. Add the last digit to reversedNum
            reversedNum = reversedNum * 10 + num % 10;

            // Remove the last digit from num
            num = num / 10;
        }

        return reversedNum;
    }

    /**
     * Method 2 (Recursive): Uses recursion to place the last digit in its
     * correct (first) position.
     * <p>
     * It takes the last digit ({@code num % 10}) and multiplies it by
     * 10^(nDigits-1) to move it to the front. Then, it adds the result
     * of the recursive call on the remaining number ({@code num / 10}).
     *
     * @param num     The number to reverse.
     * @param nDigits The number of digits in {@code num}.
     * @return The reversed number.
     */
    public static int ReversedNumRecursively(int num, int nDigits) {
        // Base case: If the number is 0, we are done.
        if (num == 0) return 0;

        // 1. Get the last digit (e.g., 123 -> 3)
        int lastDigit = num % 10;

        // 2. Place it in its correct position (e.g., 3 * 10^2 -> 300)
        int positionedDigit = (int) (lastDigit * Math.pow(10, nDigits - 1));

        // 3. Recurse on the remaining number (e.g., 12) with one less digit
        return positionedDigit + ReversedNumRecursively(num / 10, nDigits - 1);
    }

    public static void main(String[] args) {
        // TestCase 1 (using Iterative)
        int a = 8468413;
        System.out.println("Iterative reverse of " + a + "\nis: " + ReversedNumIteratively(a));
        // Expected: 3148648

        System.out.println("================================");

        // TestCase 2 (using Recursive)
        int b = 12345;
        int bDigits = 5;
        System.out.println("Recursive reverse of " + b + "\nis: " + ReversedNumRecursively(b, bDigits));
        // Expected: 54321

        System.out.println("================================");

        // TestCase 3 (using Recursive with trailing zero)
        int c = 98700;
        int cDigits = 5;
        System.out.println("Recursive reverse of " + c + "\nis: " + ReversedNumRecursively(c, cDigits));
        // Expected: 789 (Note: trailing zeros are lost, leading zeros in the
        // result are also lost as it's an int)
    }
}