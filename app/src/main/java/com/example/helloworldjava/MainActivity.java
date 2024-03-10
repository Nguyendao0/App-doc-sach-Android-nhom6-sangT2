package com.example.serach_display;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Spinner listSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Design spinner for quick search by catergory//
        listSpinner = (Spinner) findViewById(R.id.spinner);
        ArrayList<String> arrayCatergory = new ArrayList<String>();
        arrayCatergory.add("Phieu luu");
        arrayCatergory.add("Ao tuong");
        arrayCatergory.add("Tinh cam");
        arrayCatergory.add("Thanh tinh");
        ArrayAdapter adapterSpinner = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arrayCatergory );
        listSpinner.setAdapter(adapterSpinner);
        //Adapter send data to list view//
        ListView listView;
        ArrayList<Book> arrayList;
        AdapterListBook adapter;
        listView = findViewById(R.id.listBook);
        arrayList = new ArrayList<>();
        arrayList.add(new Book("Tận hưởng chuyến hành trình của mình", "Hãy cho nỗi đau thêm thời gian",R.drawable.sach1));
        arrayList.add(new Book("Tận hưởng chuyến hành trình của mình", "Hãy cho nỗi đau thêm thời gian",R.drawable.sach1));
        arrayList.add(new Book("Tận hưởng chuyến hành trình của mình", "Hãy cho nỗi đau thêm thời gian",R.drawable.sach1));
        arrayList.add(new Book("Tận hưởng chuyến hành trình của mình", "Hãy cho nỗi đau thêm thời gian",R.drawable.sach1));
        arrayList.add(new Book("Tận hưởng chuyến hành trình của mình", "Hãy cho nỗi đau thêm thời gian",R.drawable.sach1));
        arrayList.add(new Book("Tận hưởng chuyến hành trình của mình", "Hãy cho nỗi đau thêm thời gian",R.drawable.sach1));
        adapter = new AdapterListBook(MainActivity.this, R.layout.modelbook,arrayList);
        listView.setAdapter(adapter);

    }
}