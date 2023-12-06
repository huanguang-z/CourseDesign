package Part_1;
public class Queue_LinkedList {
    public class Node{
        int data;
        Node next;
    }

    Node first;
    Node last;

    public Queue_LinkedList() {
    }

    public void EnQueue(int data){
        Node oldlast=last;
        last=new Node();
        last.data = data;
        last.next=null;
        if(IsEmpty())
            first=last;
        else
            oldlast.next=last;
    }

    public boolean IsEmpty(){
        return first==null;
    }

    public int DeQueue(){
        int data=first.data;
        first=first.next;
        if(IsEmpty())
            last=null;
        return data;
    }

    public void Print(){
        Node tmp=this.first;
        while(tmp!=null){
            System.out.println(tmp.data);
            tmp=tmp.next;
        }
    }
}
