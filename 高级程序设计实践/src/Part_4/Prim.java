package Part_4;

public class Prim {
    private final int[][] matrix;
    private final boolean[] judge;
    PriorityQueues priorityQueues =  new PriorityQueues<Edge>();
    private final Edge[] finalEdges;

    public Prim(int maxsize, Edge[] edges) {
        finalEdges = new Edge[maxsize-1];
        matrix = new int[maxsize + 1][maxsize + 1];
        judge = new boolean[maxsize + 1];
        for (int i = 1; i < maxsize; i++){
            matrix[i][i] = 1;
        }
        for (Edge edge : edges) {
            matrix[edge.from()][edge.to()] = edge.weight();
            matrix[edge.to()][edge.from()] = edge.weight();
        }
    }

    public void showMatrix(){
        for (int i = 1; i< matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("\n");
        }
    }
    public Edge[] createMST(int vertex){
        int N = 0;
        Edge cur;
        judge[vertex] = true;
        updateQueues(vertex);
        while(!isDone()){
            cur = (Edge) priorityQueues.delMin();
            if (judge[cur.to()]) continue;
            finalEdges[N++] = cur;
            judge[cur.to()] = true;
            updateQueues(cur.to());
        }
        return finalEdges;
    }
    private void updateQueues(int vertex){
        for (int i = 1; i < matrix[vertex].length; i++) {
            if (matrix[vertex][i] == 0 || vertex == i) continue;
            priorityQueues.insert(new Edge(vertex,i,matrix[vertex][i]));
        }
    }
    public boolean isDone(){
        for (int i = 1; i < judge.length; i++) {
            if (!judge[i]){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int maxsize = 5;
        Edge[] edge = new Edge[8];
        edge[0] = new Edge(1,2,1);
        edge[1] = new Edge(1,3,5);
        edge[2] = new Edge(1,4,6);
        edge[3] = new Edge(2,3,2);
        edge[4] = new Edge(2,4,7);
        edge[5] = new Edge(2,5,3);
        edge[6] = new Edge(3,4,4);
        edge[7] = new Edge(4,5,3);
        Prim prim = new Prim(maxsize, edge);
        prim.showMatrix();
        Edge[] fi = prim.createMST(2);
        for (Edge value : fi) {
            System.out.println(value);
        }


    }

}
