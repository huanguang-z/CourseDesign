package Part_2;

public class Stack_LinkedList {

    private class Node {
        String item;
        Node next;
    }
    private Node first=null;

    public Stack_LinkedList() {
    }
    public boolean isEmpty() {
        return first==null;
    }
    public void push(String item) {
        Node oldFirst = first;
        first=new Node();
        first.item=item;
        first.next=oldFirst;
    }

    public String pop(){
        if(first==null)
            return null;
        String item=first.item;
        first=first.next;
        return item;
    }
}
