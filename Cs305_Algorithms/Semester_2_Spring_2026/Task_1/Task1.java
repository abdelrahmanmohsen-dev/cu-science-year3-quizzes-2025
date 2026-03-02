package Semester_2_Spring_2026.Task_1;
/**
 * CS305 - Task 1 - Spring 2026
 * Write a Java method that reads words from a file using Scanner
 * and checks whether each word contains only distinct (non-repeating) characters.
 * Return true if all words are valid,
 * otherwise return false if any word contains duplicate letters.
 * Example 1:
 * Input file: omar 
 *             ahmed
 *             mahmoud
 * Output: false (because "mahmoud" contains repeated letter 'm')
 * Example 2:
 * Input file: omar 
 *             ahmed
 *             omar
 * Output: true (because each word individually contains
 *                                           only distinct (non-repeating) letters)
*/

import java.io.*;
import java.util.*;

public class Task1 {
    /**
     * Reads all words from a file and checks whether every word
     * contains only distinct (non-repeating) characters.
     *
     * @param fileName the path of the file to read from
     * @return true if all words in the file contain distinct letters,
     *         false if at least one word contains repeated letters
     */
    public static boolean allWordsDistinctLetters(String fileName) {
        // Open the file using Scanner (we used try-with-resources to make objects closure automatic)
        try (Scanner sc = new Scanner(new File(fileName))) {
            // Loop through each line (word) in the file
            while (sc.hasNextLine()) {
                String word = sc.nextLine();
                // If any word has duplicate letters, return false
                if (!isDistinct(word)) return false;
            }
            // Handle case where file is not found
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        }
        // Return true if all words have distinct characters
        return true;
    }
    /**
     * Checks whether a given word contains only distinct characters.
     *
     * @param word the input string to be checked
     * @return true if all characters in the word are unique,
     *         false if there is at least one repeated character
     */
    public static boolean isDistinct(String word) {
        // Create a set to store unique characters
        Set<Character> set = new HashSet<>();
        for (char c : word.toCharArray()) { // Iterate over each character in the word
            if (set.contains(c)) {
                return false; // Return false if a duplicate character is found
            }
            set.add(c);
        }
        return true; // Return true if all characters are distinct
    }

    public static void main(String[] args) {
        // Test the method using input file
        System.out.println(
                allWordsDistinctLetters("input.txt"));
    }
}
