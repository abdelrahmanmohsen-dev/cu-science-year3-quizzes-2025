public class Group_1 {

    /*
    * Problem:
    *       -sum digits of an integer without using string and write it recursively.-
    *
    * Time Complexity:
    *       O(N) - since we will reach every digit of the number
    *       where: N = the number of digits
    * */

    /**
     * Method1(normal Recursive solution): just adding the last digit by taking a%10 to the recursive method without that digit
     *          with a base case: if we hit the last digit and the recursive method called with zero it return it.
     * @param a the number to sum its digits
     * @return 0 if base case is reached
     */
    public static int CountDigits(int a) {
        // Base case: if we get 0 then the method have reached every digit
        if (a == 0) return 0;

        // adding the last digit to the recursive call of the number without that digit
        return a%10 + CountDigits(a/10);
    }

    /**
     * Method2(DAC): dividing the number into two numbers until reach a number with one digit and return it and then sum them
     * @param a the number to sum its digits
     * @param nDigits the number of digis of {@code a}
     * @return {@code a} if the base case is reached
     */
    public static int CountDigitsDAC(int a, int nDigits) {
        // Base case: if the number is only one digit then return it
        if (a/10 == 0) return a;

        // calculating the mid to divide the number into two
        int mid = nDigits/2;

        // the left half of the original number
        int left = a / (int)Math.pow(10, mid);

        // the right half of th original number
        int right = a % (int)Math.pow(10, mid);

        // adding the digits with the recursive calls
        return CountDigitsDAC(left, nDigits - mid) + CountDigitsDAC(right, mid);
    }

    public static void main(String[] args) {
        // TestCase 1
        int num1 = 123456;
        System.out.println("The sum of digits of:" + num1 + "\nis: " + CountDigitsDAC(num1, 6));
        // Expected: 21

        System.out.println("================================");

        // TestCase 2
        int num2 = 587461;
        System.out.println("The sum of digits of:" + num2 + "\nis: " +CountDigits(num2));
        // Expected: 31

        System.out.println("================================");

        // TestCase 3
        int num3 = 92233720;
        System.out.println("The sum of digits of:" + num3 + "\nis: " +CountDigits(num3));
        // Expected: 28
    }
}
