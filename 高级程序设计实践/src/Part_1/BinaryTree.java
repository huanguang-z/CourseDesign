package Part_1;

public class BinaryTree {

    Node root;
    private class Node{
        int data;
        Node leftNode;
        Node rightNode;

        public Node(int data){
            this.data = data;
        }
    }

    public Node find(int key) {
        Node current = root;
        while (current != null) {
            if (current.data > key) {
                current = current.leftNode;
            } else if (current.data < key) {
                current = current.rightNode;
            } else {
                return current;
            }
        }
        return null;
    }

    public boolean insert(int data) {

        Node newNode = new Node(data);
        if (root == null) {
            root = newNode;
            return true;
        }
        Node current = root;
        Node parentNode = null;

        while (current != null) {
            parentNode = current;
            //当前值比新插入值大
            if (current.data > data) {
                current = current.leftNode;
                if (current == null) {
                    parentNode.leftNode = newNode;
                    return true;
                }
            } else {
                current = current.rightNode;
                if (current == null) {
                    parentNode.rightNode = newNode;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean delete(int data) {
        Node current = root;
        Node parentNode = root;
        boolean isLeftNode = false;

        while (current.data != data) {
            parentNode = current;
            if (current.data > data) {
                isLeftNode = true;
                current = current.leftNode;
            } else {
                isLeftNode = false;
                current = current.rightNode;
            }

            if (current == null) {
                return false;
            }
        }
        if (current.leftNode == null && current.rightNode == null) {
            if (current == root) {
                root = null;
            } else if (isLeftNode) {
                //如果要删除的节点为父节点的左节点 把父节点的左节点置为空
                parentNode.leftNode = null;
            } else {
                parentNode.rightNode = null;
            }
            return true;
        }
        if (current.leftNode == null && current.rightNode != null) {
            if (root == current) {
                root = current.rightNode;
            } else if (isLeftNode) {
                parentNode.leftNode = current.rightNode;
            } else {
                parentNode.rightNode = current.rightNode;
            }
        } else if (current.leftNode != null && current.rightNode == null) {
            if (root == current) {
                root = current.leftNode;
            } else if (isLeftNode) {
                parentNode.leftNode = current.leftNode;
            } else {
                parentNode.rightNode = current.leftNode;
            }
        }

        if(current.leftNode != null && current.rightNode != null){
            //获取删除节点的后继结点
            Node successor = getSuccessor(current);
            if (root == current) {
                root = successor;
            } else if (isLeftNode) {
                parentNode.leftNode = successor;
            } else {
                parentNode.rightNode = successor;
            }
        }
        return false;
    }

    public Node getSuccessor(Node delNode) {

        Node successorParent = delNode;
        Node successor = delNode;
        Node current = delNode.rightNode;
        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.leftNode;
        }
        if (successor != delNode.rightNode) {
            successorParent.leftNode = successor.rightNode;
            successor.rightNode = delNode.rightNode;
        }
        successor.leftNode = delNode.leftNode;
        return successor;
    }

    public void print(Node root){
        if (root == null) {
            return;
        }
        print(root.leftNode);
        System.out.println(root.data);;
        print(root.rightNode);
    }

    public Node getRoot(){
        return root;
    }

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.insert(4);
        binaryTree.insert(3);
        binaryTree.insert(7);
        binaryTree.insert(2);
        binaryTree.insert(5);
        binaryTree.insert(9);
        binaryTree.print(binaryTree.getRoot());
        if (binaryTree.find(4) != null){
            System.out.println("存在");
        }
        binaryTree.delete(3);
        binaryTree.print(binaryTree.getRoot());


    }
}
