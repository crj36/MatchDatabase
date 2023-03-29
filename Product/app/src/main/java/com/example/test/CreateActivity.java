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

public class CreateActivity extends AppCompatActivity {
    public String winloss = "-1";
    public int databaseSize;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DatabaseReference vDatabase = FirebaseDatabase.getInstance().getReference();
        vDatabase.child("size").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                databaseSize = Integer.valueOf(String.valueOf(task.getResult().getValue()));
            }
        });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        Switch winLossSwitch = findViewById(R.id.switchWinLoss);
        winLossSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    //win
                    winloss = "1";
                }else{
                    //loss
                    winloss = "-1";
                }
            }
        });
    }
    public void onClickBackHistory(View view){
        Intent openHistoryPage = new Intent(this, HistoryActivity.class);
        startActivity(openHistoryPage);
    }
    public void addMatch(View view){
        TextView map = findViewById(R.id.editTextMap);
        TextView agent = findViewById(R.id.editTextAgent);
        Match match = new Match(winloss, map.getText().toString(), agent.getText().toString());
        DatabaseReference vDatabase = FirebaseDatabase.getInstance().getReference();
        vDatabase.child(Integer.toString(databaseSize+1)).child("winloss").setValue(match.getWinloss());
        vDatabase.child(Integer.toString(databaseSize+1)).child("map").setValue(match.getMap());
        vDatabase.child(Integer.toString(databaseSize+1)).child("agent").setValue(match.getAgent());
        vDatabase.child("size").setValue(Integer.toString(databaseSize+1));
        Intent openLogInPage = new Intent(this, HomeActivity.class);
        startActivity(openLogInPage);
    }
}