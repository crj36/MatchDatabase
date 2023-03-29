package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView errorMessage = findViewById(R.id.textView3);
        errorMessage.setVisibility(View.INVISIBLE);
    }
    public void click(View view){
        TextView name = findViewById(R.id.editTextTextPersonName);
        TextView tag = findViewById(R.id.editTextTextPersonName3);
        boolean accountCorrect = checkIfAccountExists(name.getText().toString(), tag.getText().toString());
        if(accountCorrect){
            Intent openHomePage = new Intent(this, HomeActivity.class);
            startActivity(openHomePage);
        }else{
            TextView errorMessage = findViewById(R.id.textView3);
            errorMessage.setVisibility(View.VISIBLE);
        }
    }
    public boolean checkIfAccountExists(String name, String tag){

        if(name.equals("poa") && tag.equals("fps")){
            return true;
        }
        return false;
    }
}