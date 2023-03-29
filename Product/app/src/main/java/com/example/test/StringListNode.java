package com.example.test;

public class StringListNode {
    String data;
    StringListNode next;
    public StringListNode(){}
    public StringListNode(String data){
        this.data = data;
        this.next = null;
    }
    public StringListNode(String data, StringListNode next){
        this.data = data;
        this.next = next;
    }
    public int getLength(){
        StringListNode temp = this;
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
