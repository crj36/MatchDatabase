package com.example.test;

public class Match {
    private String winloss;
    private String map;
    private String agent;
    public Match(){}
    public Match(String w, String m, String a){
        winloss = w;
        map = m;
        agent = a;
    }
    public String toString(){
        return winloss;
    }

    public String getWinloss() {
        return winloss;
    }

    public String getMap() {
        return map;
    }

    public String getAgent() {
        return agent;
    }

    public void setWinloss(String winloss) {
        this.winloss = winloss;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

}
