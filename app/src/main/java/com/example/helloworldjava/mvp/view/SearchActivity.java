package com.example.helloworldjava.mvp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.*;

import com.example.helloworldjava.mvp.Adapter.AdapterHistorySearch;
import com.example.helloworldjava.mvp.model.CatergorySearch;
import com.example.helloworldjava.mvp.model.HistorySearch;
import com.example.helloworldjava.R;
import com.example.helloworldjava.mvp.presenter.SearchInterface;
import com.example.helloworldjava.mvp.presenter.SearchPresenter;

public class SearchActivity extends AppCompatActivity implements SearchInterface {
    //this is van phuoc
    private SearchPresenter searchPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
        searchPresenter = new SearchPresenter(this);
        searchPresenter.loadHistorySearch();
    }



    @Override
    public void SearchError() {

    }

    @Override
    public void SearchResult() {

    }

    @Override
    public void LoadHistory() {
        HistorySearch historySearch = new HistorySearch();
        ListView listView;
        listView = (ListView) findViewById(R.id.listBook);
        AdapterHistorySearch adapterListBook = new AdapterHistorySearch(this, R.layout.modelbook,historySearch.getArrayBook());
        listView.setAdapter(adapterListBook);
    }

    @Override
    public void LoadCatergory() {
        CatergorySearch catergorySearch = new CatergorySearch();
        Spinner spinner;
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item,catergorySearch.getArrayCatergory());
        spinner.setAdapter(arrayAdapter);
    }
}
