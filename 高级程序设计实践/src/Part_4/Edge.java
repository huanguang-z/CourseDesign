package Part_4;

public class Edge implements Comparable<Edge>{

    private final int v;
    private final int w;
    private final int weight;

    public Edge(int v, int w, int weight){
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int from() {
        return v;
    }

    public int to() {
        return w;
    }

    public int weight(){
        return weight;
    }
    @Override
    public int compareTo(Edge o) {
        return this.weight - o.weight;
    }


    @Override
    public String toString() {
        return v + "->" + w + " " + weight;
    }
}
