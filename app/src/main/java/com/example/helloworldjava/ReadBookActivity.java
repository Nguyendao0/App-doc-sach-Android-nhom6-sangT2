package com.example.helloworldjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ReadBookActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_book);





        ImageView imageViewComment = findViewById(R.id.imageView_comment);
        imageViewComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ReadBookActivity.this, CommentActivity.class);
                ReadBookActivity.this.startActivity(myIntent);
            }
        });


    }
    public void showPopup (View v){
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.menu_read);
        popup.setForceShowIcon(true);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.item1){
            Toast.makeText(this, "Item 1 clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (menuItem.getItemId() == R.id.item2){
            Toast.makeText(this, "Item 2 clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (menuItem.getItemId() == R.id.item3){
            Toast.makeText(this, "Item 3 clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
        else    {
            return false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_readbook, menu);
        return true;
    }
}