package com.example.helloworldjava.mvp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.helloworldjava.R;
import com.example.helloworldjava.mvp.presenter.HomeInterface;
import com.example.helloworldjava.mvp.presenter.HomePresenter;

public class HomeActivity extends AppCompatActivity implements HomeInterface {

    private HomePresenter homePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        homePresenter.loadSearching();
    }


    @Override
    public void onClick() {
        Button btnSearch;
        btnSearch = (Button) findViewById(R.id.button_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SearchActivity.class );
                startActivity(intent);
            }
        });
    }
}