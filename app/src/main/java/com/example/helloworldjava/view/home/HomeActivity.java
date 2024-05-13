package com.example.helloworldjava.view.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.helloworldjava.API.BookService;
import com.example.helloworldjava.APIResponeModel.ApiResponseSachModle;
import com.example.helloworldjava.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    ListBooksHomeRecyclerViewAdapter adapter;
    List<ApiResponseSachModle> data = new ArrayList<ApiResponseSachModle>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageView iconMoreYourLibrary = findViewById(R.id.ic_more_your_library);
//        ImageView iconMoreNewBooks = findViewById(R.id.ic_more_new_books);
//        ImageView iconMoreTrendingBooks = findViewById(R.id.ic_more_trending_books);

        List<ImageView> listMoreBooks = new ArrayList<>();

        listMoreBooks.add(iconMoreYourLibrary);
//        listMoreBooks.add(iconMoreNewBooks);
//        listMoreBooks.add(iconMoreTrendingBooks);
        Log.w("Api Start","-----------------------");
        Gson js = new Gson();

        for (ImageView imageView :listMoreBooks) {
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String titleListBooks = "";
                    if (imageView.getId() == R.id.ic_more_your_library) {
                        titleListBooks = "THƯ VIỆN CỦA BẠN";
                    }

                    Intent intent = new Intent(HomeActivity.this, ListBooksActity.class);
                    intent.putExtra("titleListBooks", titleListBooks);
                    startActivity(intent);
                }
            });
        }

//        ImageView iconMoreCategoryBooks = findViewById(R.id.ic_more_category_books);
//        iconMoreCategoryBooks.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(HomeActivity.this, BookCategoryActivity.class);
//                startActivity(intent);
//            }
//        });
        Log.w("Api Start","-----------------------");
        // data to populate the RecyclerView with
        //String[] data = {"1", "2", "3", "4"};

        String gdata = "";
//        BookService.api.ListBook("wVtlXbDWiRmCmETfixgd").enqueue(new Callback<List<ApiResponseSachModle>>() {
//            @Override
//            public void onResponse(Call<List<ApiResponseSachModle>> call, Response<List<ApiResponseSachModle>> response) {
//                if(response.isSuccessful()){
//                    Log.w("Api Start","------Sussecs-------");
//                    data = response.body();
//                    RecyclerView listYourLibraryRV = findViewById(R.id.list_your_library);
//                    listYourLibraryRV.setLayoutManager(new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false));
//                    adapter = new ListBooksHomeRecyclerViewAdapter( HomeActivity.this, data, R.layout.list_your_library_item);
//                    Log.w("Api Start","------2"+js.toJson(data)+"-------");
//                    listYourLibraryRV.setAdapter(adapter);
//
//                    // set up the RecyclerView
//                    RecyclerView listNewBooksRV = findViewById(R.id.list_new_books);
//                    listNewBooksRV.setLayoutManager(new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false));
//                    adapter = new ListBooksHomeRecyclerViewAdapter(HomeActivity.this, data, R.layout.list_books_item_home);
//                    listNewBooksRV.setAdapter(adapter);
//
//                    // set up the RecyclerView
//                    RecyclerView listBooksTrendingRV = findViewById(R.id.list_books_trending);
//                    listBooksTrendingRV.setLayoutManager(new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false));
//                    adapter = new ListBooksHomeRecyclerViewAdapter(HomeActivity.this, data, R.layout.list_books_item_home);
//                    listBooksTrendingRV.setAdapter(adapter);
//
//                    // set up the RecyclerView
//                    RecyclerView listBooksCategoryRV = findViewById(R.id.list_books_category);
//                    listBooksCategoryRV.setLayoutManager(new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false));
//                    adapter = new ListBooksHomeRecyclerViewAdapter(HomeActivity.this, data, R.layout.list_books_item_home);
//                    listBooksCategoryRV.setAdapter(adapter);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<ApiResponseSachModle>> call, Throwable throwable) {
//                Log.w("Api Start","------errorr-------");
//                Log.w("Api Start","------"+throwable.toString()+"-------");
//                Log.w("Api Start","-----------------");
//            }
//        });


    }


}