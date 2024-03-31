package com.example.helloworldjava.presenter;

public interface SearchInterface {
    //Show Error when input incorrect
    void SearchError();

    //Show list result
    void SearchResult();

    //Load hitory of searching of each user
    void LoadHistory();

    //Load Catergory of searching
    void LoadCatergory();
}
