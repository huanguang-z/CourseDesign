package Part_1;

public class Queue_Array {

    private int[] data;
    private int front;
    private int rear;

    public Queue_Array(int size){
        data=new int[size];
        front = 0;
        rear = 0;
    }

    public boolean isEmpty() {
        return front==rear;
    }
    public boolean isFull() {
        return (this.rear+1)%this.data.length==front;
    }

    public void EnQueue(int data){
        if(isFull())
            return;
        this.data[rear]=data;
        this.rear=(this.rear + 1) % this.data.length;
    }

    public int DeQueue(){
        if(isEmpty())
            return -1;
        int data=this.data[front];
        this.data[front]=0;
        this.front=(this.front + 1) % this.data.length;
        return data;
    }
    public void Print( ){
        for(int i=0;i<data.length; i++)
            System.out.println(data[i]);
    }

}
