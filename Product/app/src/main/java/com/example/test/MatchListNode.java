package com.example.test;

public class MatchListNode {
    Match data;
    MatchListNode next;
    public MatchListNode(){}
    public MatchListNode(Match data){
        this.data = data;
        this.next = null;
    }
    public MatchListNode(Match data, MatchListNode next){
        this.data = data;
        this.next = next;
    }
    public int getLength(){
        MatchListNode temp = this;
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
