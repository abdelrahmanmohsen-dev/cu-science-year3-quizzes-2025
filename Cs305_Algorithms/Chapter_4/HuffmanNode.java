package Chapter_4;

// Implements Comparable so PriorityQueue knows how to sort these nodes
public class HuffmanNode implements Comparable<HuffmanNode> {
    char symbol;
    int frequency;
    HuffmanNode left;
    HuffmanNode right;

    // Constructor 1: For Leaf Nodes (actual characters)
    public HuffmanNode(char symbol, int frequency) {
        this.symbol = symbol;
        this.frequency = frequency;
    }

    // Constructor 2: For Internal Nodes (combined sums)
    public HuffmanNode(int frequency, HuffmanNode left, HuffmanNode right) {
        this.frequency = frequency;
        this.left = left;
        this.right = right;
        this.symbol = '-'; // Internal nodes don't have a specific character
    }

    // --- Getters and Setters ---
    public char getSymbol() {
        return symbol;
    }

    public int getFrequency() {
        return frequency;
    }

    public HuffmanNode getLeft() {
        return left;
    }

    public HuffmanNode getRight() {
        return right;
    }

    public void setRight(HuffmanNode right) {
        this.right = right;
    }

    // --- Core Logic ---

    // This defines the "Priority": Lower frequency = Higher priority (comes first)
    @Override
    public int compareTo(HuffmanNode o) {
        return Integer.compare(this.frequency, o.frequency);
    }

    // Helper for debugging/printing
    @Override
    public String toString() {
        return "Node{" + "char=" + symbol + ", freq=" + frequency + "}";
    }
}