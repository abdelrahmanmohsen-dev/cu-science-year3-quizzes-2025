import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanAlgo {




    /**
     * Main function to build the tree and generate codes.
     * @param symbols Array of characters (e.g., {'a', 'b'})
     * @param frequency Array of corresponding frequencies (e.g., {5, 9})
     * @return A Map connecting each character to its binary code string.
     */
    public static Map<Character, String> huffman(char[] symbols, int[] frequency) {
        // 1. Create a PriorityQueue to store nodes.
        // It uses the compareTo method in HuffmanNode to order them by frequency (smallest first).
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();

        // 2. Initial Population: Create leaf nodes for all characters and add to queue
        for (int i = 0; i < symbols.length; i++) {
            pq.add(new HuffmanNode(symbols[i], frequency[i]));
        }

        // 3. Tree Construction: Loop N-1 times to merge all nodes into one tree
        // Your logic: Start from 1 up to length-1 is mathematically correct for N merges.
        for (int i = 1; i <= frequency.length - 1; i++) {
            // Remove the two nodes with the smallest frequencies
            HuffmanNode p = pq.poll();
            HuffmanNode q = pq.poll();

            // Create a new internal parent node with the sum of their frequencies
            // p becomes left child, q becomes right child
            pq.add(new HuffmanNode(p.getFrequency() + q.getFrequency(), p, q));
        }

        // 4. The last remaining node is the Root of the Huffman Tree
        HuffmanNode root = pq.poll();

        // 5. Generate the codes by traversing the tree
        Map<Character, String> map = new HashMap<>();
        preOrderTraversal(map, root, ""); // Start with an empty string

        return map;
    }

    /**
     * Recursive function to traverse the tree and build the binary strings.
     * @param codeMap The map to store results (renamed from 'frequency' for clarity)
     * @param root The current node
     * @param s The code string built so far
     */
    public static void preOrderTraversal(Map<Character, String> codeMap, HuffmanNode root, String s) {
        // Base case: if node is null, stop
        if (root == null) {
            return;
        }

        // Check if it is a Leaf Node (no children)
        // If yes, we found a character. Map the character to the current string 's'
        if (root.getLeft() == null && root.getRight() == null) {
            codeMap.put(root.getSymbol(), s);
            return;
        }

        // Recursive Step:
        // Go Left: Append "0"
        // ENHANCEMENT: Removed the space from " 0" to ensure correct binary format (e.g., "010")
        preOrderTraversal(codeMap, root.getLeft(), s + "0");

        // Go Right: Append "1"
        preOrderTraversal(codeMap, root.getRight(), s + "1");
    }
    public static void main(String[] args) {
        char[] symbols = { 'a', 'b', 'c', 'd', 'e', 'f' };
        int[] frequency = { 5, 9, 12, 13, 16, 45 };

        System.out.println("--- Running Huffman Test ---");

        Map<Character, String> result = huffman(symbols, frequency);

        System.out.println("Character | Code");
        System.out.println("----------------");
        for (char c : symbols) {
            System.out.println("    " + c + "     | " + result.get(c));
        }
    }
}