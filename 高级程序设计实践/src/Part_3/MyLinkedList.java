package Part_3;

public class MyLinkedList<Key extends Comparable<Key>>{

    private Node first;
    int size;

    private class Node{
        Key data;
        Node next;

        public Node(){}
        public Node(Key data){
            this.data = data;
        }
    }

    public MyLinkedList(){
        first = new Node( null);
    }

    public void insert(Key data){
        size++;
        Node temp = first;
        Node newNode = new Node(data);
        while(temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    public boolean delete(Key data){
        Node cur = first.next;
        Node last = first;
        while (cur != null){
            if(cur.data == data) {
                last.next = cur.next;
                return true;
            }
            last = cur;
            cur = cur.next;
        }
        return false;
    }

    public void print(){
        Node tempNode  = first.next;
        while(tempNode != null){
            System.out.println(tempNode.data);
            tempNode = tempNode.next;
        }
    }

    public int size() {
        return size;
    }

    public Key get(int index){
        Node temp = first.next;
        while (index > 0) {
            temp = temp.next;
            index--;
        }
        return temp.data;
    }

    public boolean isEmpty(){
        return first.next == null;
    }

}
