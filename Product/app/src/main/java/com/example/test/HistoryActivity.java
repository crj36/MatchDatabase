package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Time;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

public class HistoryActivity extends AppCompatActivity {
    private DatabaseReference vDatabase;
    public int databaseSize;
    public LinkedMatchList linkedMatchList = new LinkedMatchList();
    public String inScrollBox="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vDatabase = FirebaseDatabase.getInstance().getReference();
        vDatabase.child("size").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                databaseSize = Integer.valueOf(String.valueOf(task.getResult().getValue()));
            }
        });
        setContentView(R.layout.activity_history);

        //Reversing the list
        Switch switch1 = findViewById(R.id.switch1);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //Reverse the order using a stack (last in first out)
                Stack<Match> stack = new Stack<>();
                for(int i =0; i<linkedMatchList.size();i++){
                    stack.push(linkedMatchList.get(i));
                }
                while(!(linkedMatchList.size()==0)){
                    linkedMatchList.remove(0);
                }
                int stacksize= stack.size();
                for(int i =0; i<stacksize; i++){
                    linkedMatchList.add(stack.pop());
                }

                //refresh the box
                inScrollBox="";
                for(int i = 0; i< linkedMatchList.size(); i++){
                    inScrollBox += "Match " + Integer.toString(i+1) + ": ";
                    String winloss = "";
                    if(linkedMatchList.get(i).getWinloss().equals("-1")) {
                        winloss = "Loss";
                    }else{
                        winloss = "Win";
                    }
                    inScrollBox += winloss + " | ";
                    inScrollBox += linkedMatchList.get(i).getMap() + " | ";
                    inScrollBox += linkedMatchList.get(i).getAgent() +"\n";
                }
                TextView inScroll = findViewById(R.id.textViewInScroll);
                inScroll.setText(inScrollBox);
            }
        });


    }
    public void fillMatchList(){
        // Update the Match Linked List so it can be used in the app!
        recursionMatchesFromDatabase(1);
    }
    public void recursionMatchesFromDatabase(int ID){
        Match match = new Match();
        vDatabase.child(Integer.toString(ID)).child("winloss").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {

            public void onComplete(@NonNull Task<DataSnapshot> task) {
                match.setWinloss(String.valueOf(task.getResult().getValue()));
            }
        });
        vDatabase.child(Integer.toString(ID)).child("map").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {

            public void onComplete(@NonNull Task<DataSnapshot> task) {
                match.setMap(String.valueOf(task.getResult().getValue()));
            }
        });
        vDatabase.child(Integer.toString(ID)).child("agent").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {

            public void onComplete(@NonNull Task<DataSnapshot> task) {
                match.setAgent(String.valueOf(task.getResult().getValue()));
            }
        });

        linkedMatchList.add(match);
        if(ID<databaseSize){
            recursionMatchesFromDatabase(ID+1);
        }
    }
    public void onClickBackHome(View view){
        Intent openHomePage = new Intent(this, HomeActivity.class);
        startActivity(openHomePage);
    }
    public void onClickCreatePage(View view){
        Intent openCreatePage = new Intent(this, CreateActivity.class);
        startActivity(openCreatePage);
    }
    public void onClickRefresh(View view){
        fillMatchList();
    }
    public void onClickRefresh2(View view){
        for(int i = 0; i< linkedMatchList.size(); i++){
            inScrollBox += "Match " + Integer.toString(i+1) + ": ";
            String winloss = "";
            if(linkedMatchList.get(i).getWinloss().equals("-1")) {
                winloss = "Loss";
            }else{
                winloss = "Win";
            }
            inScrollBox += winloss + " | ";
            inScrollBox += linkedMatchList.get(i).getMap() + " | ";
            inScrollBox += linkedMatchList.get(i).getAgent() +"\n";
        }
        TextView inScroll = findViewById(R.id.textViewInScroll);
        inScroll.setText(inScrollBox);


    }
}