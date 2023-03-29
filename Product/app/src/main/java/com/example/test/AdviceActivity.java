package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class AdviceActivity extends AppCompatActivity {
    public DatabaseReference vDatabase;
    public int databaseSize;
    public LinkedMatchList linkedMatchList = new LinkedMatchList();
    public LinkedListList linkedListList = new LinkedListList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice);
        vDatabase = FirebaseDatabase.getInstance().getReference();
        vDatabase.child("size").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                databaseSize = Integer.valueOf(String.valueOf(task.getResult().getValue()));
            }
        });
        LinkedStringList s1 = new LinkedStringList();
        s1.add("ascent");
        s1.add("Map Description:\nAscent was the first new map to be added since Valorant's official release.It's a two site map that is located in Venice, Italy. Compared to the other maps, Ascent has the most open area with a large courtyard at the center of the map.");
        linkedListList.add(s1);
        LinkedStringList s2 = new LinkedStringList();
        s2.add("bind");
        s2.add("Map Description:\nBind's 'unique' feature is that it doesn't contain a mid section, instead having two one-way teleporters. One takes players from A Short to B Short and the other takes players from B Long to A Lobby.");
        linkedListList.add(s2);
        LinkedStringList s3 = new LinkedStringList();
        s3.add("split");
        s3.add("Map Description:\nSplit has two Spike locations and three main paths - two on the sides and one in the middle (Mid). Split favours the defensive team because there are only two narrow routes leading to the charge planting spot, making them easier to defend.");
        linkedListList.add(s3);
    }
    public void onClickBackHomeFromAdvice(View view){
            Intent openHomePage = new Intent(this, HomeActivity.class);
            startActivity(openHomePage);
    }
    public void onClickCalculate(View view){
        TextView t = findViewById(R.id.editTextTextPersonName2);
        String map = t.getText().toString();
        HashMap<String, ArrayList<Integer>> matchesWithMap = new HashMap<>();
        //this hashmap stores the w/l ratio of agents playing on this specific map
        for(int i = 0; i<linkedMatchList.size(); i++){
            if(linkedMatchList.get(i).getMap().equals(map)){
                if(matchesWithMap.containsKey(linkedMatchList.get(i).getAgent())){
                    if(linkedMatchList.get(i).getWinloss().equals("1")){
                        int temp = matchesWithMap.get(linkedMatchList.get(i).getAgent()).get(0);// get amount of wins
                        matchesWithMap.get(linkedMatchList.get(i).getAgent()).set(0, temp+1);//change it to one more
                    }else{
                        int temp = matchesWithMap.get(linkedMatchList.get(i).getAgent()).get(1);//get amount of losses
                        matchesWithMap.get(linkedMatchList.get(i).getAgent()).set(1, temp+1);//change it to one more
                    }
                }else{
                    ArrayList<Integer> temp = new ArrayList<>();
                    if(linkedMatchList.get(i).getWinloss().equals("-1")){
                        temp.add(0); //wins
                        temp.add(1); //losses
                    }else{
                        temp.add(1);
                        temp.add(0);
                    }
                    matchesWithMap.put(linkedMatchList.get(i).getAgent(), temp);
                }
            }
        }
        String besta = "jett";
        String worsta = "jett";
        double bestr = -999.0;
        double worstr = 999.0;
        for (HashMap.Entry<String, ArrayList<Integer>> cycle : matchesWithMap.entrySet()) {
            System.out.println(cycle.getKey() + " = " + cycle.getValue());
            int wins = cycle.getValue().get(0);
            int losses = cycle.getValue().get(1);
            double ratio = 0.0;
            if(!(losses==0)){
                ratio = (double)wins/(double)losses;
            }else{
                ratio = 999.0;
            }
            if(ratio>bestr){
                besta = cycle.getKey();
                bestr = ratio;
            }
            if(ratio<worstr){
                worsta = cycle.getKey();
                worstr = ratio;
            }
        }
        TextView best = findViewById(R.id.textViewBest);
        TextView worst = findViewById(R.id.textViewWorst);
        String ratioprint="";
        if(bestr == 999.0){
            ratioprint= "undefeated";
        }else{
            ratioprint = Double.toString(bestr);
        }
        best.setText("Best agent: " + besta + ", w/l ratio is " + ratioprint);

        worst.setText("Worst agent: " + worsta + ", w/l ratio is " + Double.toString(worstr));


        //Set map description
        TextView mapdescription = findViewById(R.id.textViewMapDescription);
        for(int i =0; i<linkedListList.size(); i++){
            for(int y=0; y<linkedListList.get(i).size(); y++){
                if(linkedListList.get(i).get(0).equals(map)){
                    mapdescription.setText(linkedListList.get(i).get(1));
                }
            }
        }
    }
    public void onClickRefreshAdvice(View view){
        vDatabase = FirebaseDatabase.getInstance().getReference();
        // Update the Match Linked List so it can be used in the app!
        for(int i =1; i<=databaseSize; i++){
            Match match = new Match();
            vDatabase.child(Integer.toString(i)).child("winloss").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {

                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    match.setWinloss(String.valueOf(task.getResult().getValue()));
                }
            });
            vDatabase.child(Integer.toString(i)).child("map").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {

                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    match.setMap(String.valueOf(task.getResult().getValue()));
                }
            });
            vDatabase.child(Integer.toString(i)).child("agent").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {

                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    match.setAgent(String.valueOf(task.getResult().getValue()));
                }
            });

            linkedMatchList.add(match);
        }
    }
}