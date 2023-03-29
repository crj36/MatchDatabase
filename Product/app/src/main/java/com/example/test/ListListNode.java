package com.example.test;

public class ListListNode {
    LinkedStringList data;
    ListListNode next;
    public ListListNode(){}
    public ListListNode(LinkedStringList data){
        this.data = data;
        this.next = null;
    }
    public ListListNode(LinkedStringList data, ListListNode next){
        this.data = data;
        this.next = next;
    }
    public int getLength(){
        ListListNode temp = this;
        int length = 0;
        while(true){
            if(temp == null){
                break;
            }
            temp = temp.next;
            length +=1;
        }
        return length;
    }
}
