package com.example.test;


public class LinkedMatchList {
    private MatchListNode front;
    public LinkedMatchList(){
        front = null;
    }
    //adds the given value to the end of the list
    public void add(Match value){
        if(front == null){
            front = new MatchListNode(value);
        }else{
            MatchListNode current = front;
            while(current.next!=null){
                current = current.next;
            }
            current.next = new MatchListNode(value);
            //adding to the end of an existing list
        }
    }
    public String toString(){
        String result;
        if(front == null){
            return "[]";
        }else{
            result = "[" + front.data;
            MatchListNode current = front.next;
            while(current != null){
                result +="," + current.data;
                current = current.next;
            }
            result += "]";
        }
        return result;
    }


    public Match get(int index){
        MatchListNode current = front;
        for(int i=0; i<index; i++){
            current=current.next;
        }
        return current.data;
    }
    public void remove(int index) {
        if (index == 0) {
            // special case: removing first element
            front = front.next;
        } else {
            // removing from elsewhere in the list
            MatchListNode current = front;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
        }
    }
    public int size(){
        int size = 0;
        MatchListNode current = front;
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