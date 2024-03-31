package com.example.helloworldjava.presenter;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;


public class SearchPresenter {
    private SearchInterface searchInterface;

    public SearchPresenter(SearchInterface searchInterface) {
        this.searchInterface = searchInterface;
    }

    public void checkInputSearch(String text){

    }

    public void getResult(String text){

    }

    public void loadHistorySearch(){
        searchInterface.LoadHistory();
        searchInterface.LoadCatergory();
    }
}
