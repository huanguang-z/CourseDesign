package Part_2;
public class Stack_Array {

    private String[]s;
    private int N=0;

    public Stack_Array() {
        s=new String[1];
    }

    public boolean isEmpty() {
        return N==0;
    }

    public void resize(int size){
        String []copy=new String[size];
        for(int i=0;i<s.length;i++){
            copy[i] = s[i];
        }
        s=copy;
    }

    public void push(String item){
        if(N==s.length){
            resize(2*s.length);
        }
        s[N++]=item;
    }

    public String pop() {
        String item=s[--N];
        s[N]=null;
        if(N>0&&N==s.length/4){
            resize(s.length/2);
        }
        return item;
    }
}
