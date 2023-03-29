package com.example.test;


public class LinkedStringList {
    private StringListNode front;
    public LinkedStringList(){
        front = null;
    }
    //adds the given value to the end of the list
    public void add(String value){
        if(front == null){
            front = new StringListNode(value);
        }else{
            StringListNode current = front;
            while(current.next!=null){
                current = current.next;
            }
            current.next = new StringListNode(value);
            //adding to the end of an existing list
        }
    }
    public String toString(){
        String result;
        if(front == null){
            return "[]";
        }else{
            result = "[" + front.data;
            StringListNode current = front.next;
            while(current != null){
                result +="," + current.data;
                current = current.next;
            }
            result += "]";
        }
        return result;
    }
    public String get(int index){
        StringListNode current = front;
        for(int i=0; i<index; i++){
            current=current.next;
        }
        return current.data;
    }
    public int size(){
        int size = 0;
        StringListNode current = front;
        while(true){
            if(current == null){
                break;
            }else{
                size+=1;
                current = current.next;
            }
        }
        return size;
    }
}