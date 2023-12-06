package Learning;

import java.util.*;
import java.math.*;
public class IsAVL {
    private class Node{
        int Value;
        Node left, right;

        public Node(int value, Node left, Node right) {
            Value = value;
            this.left = left;
            this.right = right;
        }
    }
    private Node root=null;
    boolean flag;
    private Node Insert(Node node,int num) {
        if(root==null){
            root = new Node(num,null,null);
        }
        if(num<root.Value)
            root.left=Insert(root.left,num);
        else if(num>root.Value)
            root.right=Insert(root.right,num);
        return root;
    }

    private int DFSCheck(Node node) {
        int LeftHeight=0,RightHeight=0;
        if(root.left != null)
            LeftHeight=DFSCheck(root.left);
        if(root.right!=null)
            RightHeight = DFSCheck(root.right);
        if(Math.abs(LeftHeight-RightHeight)>1)
            flag=false;
        return 1+Math.max(LeftHeight,RightHeight);
    }
    public void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int k = input.nextInt();
        int n,num;
        for(int i = 0; i < k; i++){
            n=input.nextInt();
            root=null;
            flag = true;
            for(int j = 0; j < n; j++){
                num=input.nextInt();
                root=Insert(root,num);
            }
            int tmp = DFSCheck(root);
            if(flag)
                System.out.println("Yes");
            else
                System.out.println("No");
        }
    }
}

