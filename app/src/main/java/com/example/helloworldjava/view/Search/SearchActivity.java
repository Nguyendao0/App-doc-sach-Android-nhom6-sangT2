package com.example.helloworldjava.view.Search;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.helloworldjava.Adapter.AdapterHistorySearch;
import com.example.helloworldjava.R;
import com.example.helloworldjava.model.CatergorySearch;
import com.example.helloworldjava.model.HistorySearch;
import com.example.helloworldjava.presenter.SearchInterface;
import com.example.helloworldjava.presenter.SearchPresenter;

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
        AdapterHistorySearch adapterListBook = new AdapterHistorySearch(this, R.layout.search_history_book,historySearch.getArrayBook());
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
