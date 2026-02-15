package AddtionalProblems.Divide_and_Conquer;/*
Problem: Frequency of Characters using Divide and Conquer
------------------------------------------------------------
Description:
Write a recursive program that counts the frequency of each character
in a given lowercase string using the divide and conquer approach.

Example:
Input: "abood"
Output:
a:1
b:1
d:1
o:2

Approach:
- Divide the string into two halves.
- Recursively count character frequencies in each half.
- Combine results in a shared frequency array.
*/

public class frequencyCharacters {

    static int[] freq = new int['z' + 1]; // Frequency array to store counts of characters

    public static void main(String[] args) {
        // Test Case 1
        String s1 = "abood";
        frequencyCharacter(s1, 0, s1.length() - 1);
        System.out.println("Test 1 - Input: " + s1);
        printFrequencies();

        // Reset frequency array for next test
        freq = new int['z' + 1];

        // Test Case 2
        String s2 = "banana";
        frequencyCharacter(s2, 0, s2.length() - 1);
        System.out.println("\nTest 2 - Input: " + s2);
        printFrequencies();

        // Reset frequency array for next test
        freq = new int['z' + 1];

        // Test Case 3
        String s3 = "mississippi";
        frequencyCharacter(s3, 0, s3.length() - 1);
        System.out.println("\nTest 3 - Input: " + s3);
        printFrequencies();
    }

    // Recursive function to count character frequencies
    public static void frequencyCharacter(String str, int l, int r) {
        if (l == r) {
            freq[str.charAt(l)]++; // Increment frequency of current character
            return;
        }
        int mid = (l + r) / 2; // Find midpoint
        frequencyCharacter(str, l, mid); // Recurse on left half
        frequencyCharacter(str, mid + 1, r); // Recurse on right half
    }

    // Helper function to print non-zero character frequencies
    public static void printFrequencies() {
        for (char i = 'a'; i <= 'z'; i++) {
            if (freq[i] > 0) {
                System.out.println(i + ": " + freq[i]);
            }
        }
    }
}

