package com.example.helloworldjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;



public class TESTROOM extends AppCompatActivity {

    EditText txt;
    EditText txt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testroom);
        txt = findViewById(R.id.editTextText);
        txt2 = findViewById(R.id.editTextText2);
    }


}