package task_1.group_5;

import java.util.Stack;

public class group_5 {
    /*
    * Problem:
    *       -Reverse the words in a sentence (without using split()) using O(n) algorithm-
    *
    * Solving Methods:
    *       Method_1:
    *               1) Using a stack to store every string in the sentence so it pops them in reverse order
    *               2) StringBuilder to form every string character by character
    *               3) Loop over the sentence and add characters to the StringBuilder until find a space, Then add this word to the stack and empty the StringBuilder
    *               4) Adding the words again to the StringBuilder and return it as String
    *
    *       Method_2:
    *               1) Also I will use StringBuilder, but it will store the reversed sentence
    *               2) Initialize a pointer to the end of the sentence
    *               3) Loop over the sentence until find a space then use the substring method from the character after the space to the end pointer
    *               4) Update the end pointer to the current character (the space)
    *               5) Return the StringBuilder as string
    *
    * Time Complexity:
    *       Since I will iterate over the sentence once it will be =  O(N)
    * */
    public static String reverseSentence(String str) {
        // Initialize the stack, StringBuilder
        Stack<String> words = new Stack<>();
        StringBuilder currentWord = new StringBuilder();
        int n = str.length();

        // Loop over the sentence and cut when find space character (' ')
        for (int i = 0; i < n; i++) {
            if (str.charAt(i) == ' ') {
                words.push(currentWord.toString());
                currentWord = new StringBuilder();
            }
            else currentWord.append(str.charAt(i));
        }

        // Adding the last word to the Stack
        words.push(currentWord.toString());
        currentWord = new StringBuilder();

        // Adding the strings in reveres to the StringBuilder
        while (!words.isEmpty()) {
            currentWord.append(words.pop()).append(' ');
        }
        return  currentWord.toString().trim();
    }

    public static String reverseSentence_pointerMethod(String str) {
        // Initialize the StringBuilder, end Pointer
        StringBuilder reversed = new StringBuilder();
        int end = str.length();

        // Loop from end to begin and use the substring method when encounter space (' ')
        for (int i = str.length() - 1; i >= 0; i--) {
            if (str.charAt(i) == ' ') {
                reversed.append(str.substring(i + 1, end)).append(' ');
                // Updating the pointer to the current location
                end = i;
            }
        }
        // Adding the last string to the StringBuilder
        reversed.append(str.substring(0, end));
        return reversed.toString();
    }

    // TestCases
    public static void main(String[] args) {
        // Test1
        String str = "Hello World!";
        System.out.println("Original String: " + str);
        str = reverseSentence_pointerMethod(str);
        System.out.println("Reversed String: "+ str);
        // Expected: "World! Hello"

        System.out.println();
        System.out.println("===============================");
        System.out.println();

        // Test2
        String str2 = "I Love Java!";
        System.out.println("Original String: " + str2);
        str2 = reverseSentence(str2);
        System.out.println("Reversed String: "+ str2);
        // Expected: "Java! Love I"

        System.out.println();
        System.out.println("===============================");
        System.out.println();

        // Test3
        String str3 = "practice! about is Algorithms";
        System.out.println("Original String: " + str3);
        str3 = reverseSentence_pointerMethod(str3);
        System.out.println("Reversed String: "+ str3);
        // Expected: "Algorithms is about practice!"
    }
}
