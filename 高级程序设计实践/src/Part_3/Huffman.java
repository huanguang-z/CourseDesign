package Part_3;

public class Huffman {

    private boolean flag ;
    private String str;
    private Node root;
    PriorityQueues<Node> priorityQueues;
    MyLinkedList<CharData> myLinkedList;


    private class CharData implements Comparable<CharData>{
        int num = 1;
        char c;

        public CharData(char ch){
            this.c = ch;
        }

        @Override
        public int compareTo(CharData o) {
            return this.c - o.c;
        }
    }

    private class Node implements Comparable<Node>{

        public String code = "";
        public String data = "";
        public int count;
        public Node leftNode;
        public Node rightNode;

        public Node(String data, int count){
            this.data = data;
            this.count = count;
        }

        public Node(String data, int count, Node leftNode, Node rightNode){
            this.data = data;
            this.count = count;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }

        public Node(int count, Node leftNode, Node rightNode){
            this.count = count;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }

        @Override
        public int compareTo(Node node) {
            return this.count - node.count;
        }
    }

    public void createHfmTree(String str){
        this.str = str;

        myLinkedList = new MyLinkedList<CharData>();
        priorityQueues = new PriorityQueues<Node>();

        getCharNum(str);

        createNode();

        createTree();

        root = priorityQueues.delMin();
    }

    private void getCharNum(String str) {
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            flag = true;
            for (int j = 0; j < myLinkedList.size; j++) {
                CharData charData = myLinkedList.get(j);

                if (ch == charData.c){
                    charData.num++;
                    flag = false;
                    break;
                }
            }
            if(flag){
                myLinkedList.insert(new CharData(ch));
            }
        }
    }

    private void createNode() {
        for (int i = 0; i < myLinkedList.size(); i++) {
            String data = myLinkedList.get(i).c + "";
            int count = myLinkedList.get(i).num;

            Node node = new Node(data,count);
            priorityQueues.insert(node);
        }
    }

    private void createTree() {
        while (priorityQueues.size() > 1) {
            Node left = priorityQueues.delMin();
            Node right = priorityQueues.delMin();

            left.code = "0";
            right.code = "1";

            setCode(left);
            setCode(right);

            int parentWeight = left.count + right.count;
            Node parent = new Node(parentWeight,left,right);

            priorityQueues.insert(parent);

        }
    }

    private void setCode(Node root) {

        if (root.leftNode != null) {
            root.leftNode.code = root.code + "0";
            setCode(root.leftNode);
        }

        if (root.rightNode != null) {
            root.rightNode.code = root.code + "1";
            setCode(root.rightNode);
        }
    }

    private void show(Node node) {
        if(node.leftNode == null && node.rightNode == null) {
            System.out.println(node.data + ": " + node.code);
        }
        if (node.leftNode != null) {
            show(node.leftNode);
        }

        if (node.rightNode != null) {
            show(node.rightNode);
        }
    }

    public void show () {
        show(root);
    }


    public static void main(String[] args) {
        Huffman huffman = new Huffman();
        String str = "abbccccccccccccccddddfffffooo";
        huffman.createHfmTree(str);
        huffman.show();
    }

}
