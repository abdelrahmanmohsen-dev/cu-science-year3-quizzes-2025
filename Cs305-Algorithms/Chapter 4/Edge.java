package Chapter4;

/**
 * A simple Data structure to represent a weighted edge in a graph.
 * Used by graph algorithms like Prim's.
 */
public class Edge implements Comparable<Edge> {
    int from;   // The starting vertex
    int to;     // The ending vertex
    int weight; // The cost/distance of the edge

    // Constructor to initialize an edge
    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    // --- Getters and Setters ---

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    // toString method for easy printing of Edge details
    @Override
    public String toString() {
        return "Edge{" +
                "from=" + from +
                ", to=" + to +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.weight, o.weight);
    }
}