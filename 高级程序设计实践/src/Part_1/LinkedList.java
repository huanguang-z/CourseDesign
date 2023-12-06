package Part_1;

import org.w3c.dom.Node;

public class LinkedList<Key extends Comparable<Key>>{
    private Node first;
    private class Node{
        Key data;
        Node next;
        public Node(){};
        public Node(Key data){
            this.data = data;
        }
    }
    public LinkedList(){
        first=new Node(null);
    }

    public void insert(Key key){
        Node tmp=first;
        Node node=new Node(key);
        while(tmp.next != null)
            tmp=tmp.next;
        tmp.next = node;
    }
    public boolean delete(Key key){
        Node cur=first.next;
        Node last=first;
        while(cur.next != null){
            if(cur.data==key){
                last.next=cur.next;
                return true;
            }
        }
        return false;
    }
    public boolean isEmpty(){
        return first.next == null;
    }
    public void sort(){
        Node pointer=first;
        Node flex=null;
        Node min=null;
        Key mid = null;
        while(pointer != null){
            min=pointer;
            flex=pointer.next;
            while(flex != null){
                if(flex.data.compareTo(min.data)<0){
                    min=flex;
                }
                flex=flex.next;
            }
            mid=min.data;
            min.data=pointer.data;
            pointer.data=mid; //仅改变数据位置，不改变指针指向
            pointer=pointer.next;
        }
    }

    public boolean IsEmpty() {
        return first.next == null;
    }

    public void print(){
        Node tmp=first.next;
        while (tmp != null) {
            System.out.println(tmp.data);
            tmp=tmp.next;
        }
    }
}

