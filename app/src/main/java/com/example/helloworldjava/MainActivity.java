package com.example.helloworldjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.helloworldjava.GioiThieuSach.BookDetailActivity;
import com.example.helloworldjava.Menu.MenuActivity;
import com.example.helloworldjava.Thongbao.Noitification;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity" ,"Hello world");
    }
    public void goToMenu(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void goToGioiThieuSach(View view) {
        Intent intent = new Intent(this, BookDetailActivity.class);
        startActivity(intent);
    }
    public void ThongBao(View view) {
        Intent intent = new Intent(this, Noitification.class);
        startActivity(intent);
    }

}