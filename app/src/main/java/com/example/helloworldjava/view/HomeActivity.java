package com.example.helloworldjava.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.helloworldjava.R;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    ListBooksHomeRecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageView iconMoreYourLibrary = findViewById(R.id.ic_more_your_library);
        ImageView iconMoreNewBooks = findViewById(R.id.ic_more_new_books);
        ImageView iconMoreTrendingBooks = findViewById(R.id.ic_more_trending_books);

        List<ImageView> listMoreBooks = new ArrayList<>();

        listMoreBooks.add(iconMoreYourLibrary);
        listMoreBooks.add(iconMoreNewBooks);
        listMoreBooks.add(iconMoreTrendingBooks);

        for (ImageView imageView :listMoreBooks) {
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String titleListBooks;
                    if (imageView.getId() == R.id.ic_more_your_library) {
                        titleListBooks = "THƯ VIỆN CỦA BẠN";
                    }
                    else if (imageView.getId() == R.id.ic_more_new_books) {
                        titleListBooks = "MỚI";
                    }
                    else {
                        titleListBooks = "PHỔ BIỂN";
                    }

                    Intent intent = new Intent(HomeActivity.this, ListBooksActity.class);
                    intent.putExtra("titleListBooks", titleListBooks);
                    startActivity(intent);
                }
            });
        }

        ImageView iconMoreCategoryBooks = findViewById(R.id.ic_more_category_books);
        iconMoreCategoryBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, BookCategoryActivity.class);
                startActivity(intent);
            }
        });


        // data to populate the RecyclerView with
        String[] data = {"1", "2", "3", "4"};

        RecyclerView listYourLibraryRV = findViewById(R.id.list_your_library);
        listYourLibraryRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter = new ListBooksHomeRecyclerViewAdapter( this, data, R.layout.list_your_library_item);
        listYourLibraryRV.setAdapter(adapter);

        // set up the RecyclerView
        RecyclerView listNewBooksRV = findViewById(R.id.list_new_books);
        listNewBooksRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter = new ListBooksHomeRecyclerViewAdapter(this, data, R.layout.list_books_item_home);
        listNewBooksRV.setAdapter(adapter);

        // set up the RecyclerView
        RecyclerView listBooksTrendingRV = findViewById(R.id.list_books_trending);
        listBooksTrendingRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter = new ListBooksHomeRecyclerViewAdapter(this, data, R.layout.list_books_item_home);
        listBooksTrendingRV.setAdapter(adapter);

        // set up the RecyclerView
        RecyclerView listBooksCategoryRV = findViewById(R.id.list_books_category);
        listBooksCategoryRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter = new ListBooksHomeRecyclerViewAdapter(this, data, R.layout.list_books_item_home);
        listBooksCategoryRV.setAdapter(adapter);

    }
}