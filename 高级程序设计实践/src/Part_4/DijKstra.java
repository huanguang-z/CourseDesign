package Part_4;

public class DijKstra {
    private final int MAX_INT= 99999999;
    private final int[][] matrix;
    private final boolean[] known;
    PriorityQueues priorityQueues =  new PriorityQueues<Edge>();
    private final int[] distance;

    private Edge[] edges;

    public DijKstra(int maxsize, Edge[] edgesIn) {
        edges = new Edge[maxsize + 1];
        distance = new int[maxsize + 1];
        matrix = new int[maxsize + 1][maxsize + 1];
        known = new boolean[maxsize + 1];
        for (int i = 1; i < maxsize + 1; i++){
            matrix[i][i] = 1;
            distance[i] = MAX_INT;
        }
        for (Edge edge : edgesIn) {
            matrix[edge.from()][edge.to()] = edge.weight();
        }
    }


    public void invoke(int vertex) {
        int N = 0;
        Edge cur;
        known[vertex] = true;
        distance[vertex] = 0;
        updateQueues(vertex);
        while (true) {
            cur = (Edge) priorityQueues.delMin();
            if (priorityQueues.isEmpty()) break;
            for (int i = 1; i < matrix[cur.to()].length; i++) {
                if (matrix[cur.to()][i] == 0 || known[cur.to()] || i == cur.to()) continue;
                priorityQueues.insert(new Edge(cur.to(), i, matrix[cur.to()][i]));
                if (distance[cur.to()] + matrix[cur.to()][i] < distance[i]){
                    distance[i] = distance[cur.to()] + matrix[cur.to()][i];
                    edges[i] = new Edge(cur.to(), i,matrix[cur.to()][i]);
                }
            }
            known[cur.to()] = true;
        }
    }


    private void updateQueues(int vertex){
        edges[vertex] = null;
        Edge temp;
        for (int i = 1; i < matrix[vertex].length; i++) {
            if (matrix[vertex][i] == 0 || vertex == i) continue;
            temp = new Edge(vertex,i,matrix[vertex][i]);
            priorityQueues.insert(temp);
            distance[i] = matrix[vertex][i];
            edges[i] = temp;
        }
    }
    public void showShortestDistance() {
        for (int i = 1; i < distance.length; i++) {
            System.out.println(distance[i]);
        }
    }

    public void showRoads() {
        for (int i = 1; i < edges.length; i++) {
            if (edges[i] == null) continue;
            System.out.println(edges[i]);
        }
    }

    public static void main(String[] args) {
        Edge[] edges = new Edge[6];
        edges[0] = new Edge(1,2,1);
        edges[1] = new Edge(1,3,3);
        edges[2] = new Edge(1,4,5);
        edges[3] = new Edge(2,4,2);
        edges[4] = new Edge(2,5,6);
        edges[5] = new Edge(4,5,1);
        DijKstra dijKstra = new DijKstra(5, edges);
        dijKstra.invoke(1);
        dijKstra.showShortestDistance();
        dijKstra.showRoads();
    }
}
