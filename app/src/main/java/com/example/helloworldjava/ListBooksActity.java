package com.example.helloworldjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

public class ListBooksActity extends AppCompatActivity {
    ListBooksRecyclerViewAdapter adapter;
    int numberOfColumns = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_books);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String titleListBooks = extras.getString("titleListBooks");
            TextView txtListBooks = findViewById(R.id.txtListMore);
            txtListBooks.setText(titleListBooks);
        }

        String[] listArrangeBooks = getResources().getStringArray(R.array.listArrangeBooks);
        ArrayAdapter<String> listArrangeBooksAdapter = new ArrayAdapter<>(this, R.layout.list_item, listArrangeBooks);
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.listArrangeBookAutoCompleTextView);
        autoCompleteTextView.setAdapter(listArrangeBooksAdapter);

        ImageView btnBackHome = findViewById(R.id.iconBackHome);
        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // data to populate the RecyclerView with
        String[] data = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48"};

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.list_book);
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        adapter = new ListBooksRecyclerViewAdapter(this, data);
        recyclerView.setAdapter(adapter);
    }
}