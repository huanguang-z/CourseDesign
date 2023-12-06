package Part_2;

public class BinaryTree {
    private Node root;
    private class Node{
        int data;
        Node leftNode;
        Node rightNode;
        public Node(int data){
            this.data = data;
        }
        public boolean insert(int data){
            Node newNode=new Node(data);
            if(root==null){
                root=newNode;
                return true;
            }
            Node current=root;
            Node parentNode =null;
            while (current != null) {
                parentNode = current;
                if(current.data > data){
                    current=current.leftNode;
                    if(current==null){
                        parentNode.leftNode= newNode;
                        return true;
                    }
                }else {
                    current = current.rightNode;
                    if(current==null){
                        parentNode.rightNode= newNode;
                        return true;
                    }
                }
            }
            return false;
        }
        public void PreOrder(Node root){
            if(root == null)
                System.out.println(root.data);
            PreOrder(root.leftNode);
            PreOrder(root.rightNode);
        }
        public void InOrder(Node root){
            if(root == null)
                return;
            InOrder(root.leftNode);
            System.out.println(root.data);
            InOrder(root.rightNode);
        }

        public void PostOrder(Node root){
            if (root == null) {
                return;
            }
            PostOrder(root.leftNode);
            PostOrder(root.rightNode);
            System.out.println(root.data);
        }
    }

}
