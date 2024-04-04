package com.example.helloworldjava.view.Library;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;


import com.example.helloworldjava.R;

public class QuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
    }

    public void backLibrary(View view) {
        onBackPressed(); // Gọi phương thức onBackPressed() để xử lý quay trở lại
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
