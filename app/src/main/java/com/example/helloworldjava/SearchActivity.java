package com.example.helloworldjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
        runArrayList();

    }
    protected void runArrayList(){
        CatergorySearch catergorySearch = new CatergorySearch();
        HistorySearch historySearch = new HistorySearch();

        Spinner spinner;
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item,catergorySearch.getArrayCatergory());
        spinner.setAdapter(arrayAdapter);

        ListView listView;
        listView = (ListView) findViewById(R.id.listBook);
        AdapterListBook adapterListBook = new AdapterListBook(this, R.layout.modelbook,historySearch.getArrayBook());
        listView.setAdapter(adapterListBook);
    }
}