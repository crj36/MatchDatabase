package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


    }
    public void onClickBackLogIn(View view){
        Intent openLogInPage = new Intent(this, MainActivity.class);
        startActivity(openLogInPage);
    }
    public void onClickAdvice(View view){
        Intent openAdvicePage = new Intent(this, AdviceActivity.class);
        startActivity(openAdvicePage);
    }
    public void onClickHistory(View view){
        Intent openHistoryPage = new Intent(this, HistoryActivity.class);
        startActivity(openHistoryPage);
    }
}