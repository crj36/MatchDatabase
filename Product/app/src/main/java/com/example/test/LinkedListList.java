package com.example.test;


public class LinkedListList {
    private ListListNode front;
    public LinkedListList(){
        front = null;
    }
    //adds the given value to the end of the list
    public void add(LinkedStringList value){
        if(front == null){
            front = new ListListNode(value);
        }else{
            ListListNode current = front;
            while(current.next!=null){
                current = current.next;
            }
            current.next = new ListListNode(value);
            //adding to the end of an existing list
        }
    }
    public String toString(){
        String result;
        if(front == null){
            return "[]";
        }else{
            result = "[" + front.data;
            ListListNode current = front.next;
            while(current != null){
                result +="," + current.data;
                current = current.next;
            }
            result += "]";
        }
        return result;
    }
    public LinkedStringList get(int index){
        ListListNode current = front;
        for(int i=0; i<index; i++){
            current=current.next;
        }
        return current.data;
    }
    public int size(){
        int size = 0;
        ListListNode current = front;
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