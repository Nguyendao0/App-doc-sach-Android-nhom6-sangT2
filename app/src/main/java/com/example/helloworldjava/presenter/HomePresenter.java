package com.example.helloworldjava.presenter;

import com.example.helloworldjava.presenter.HomeInterface;

public class HomePresenter {
    private HomeInterface homeInterface;

    public HomePresenter(HomeInterface homeInterface) {
        this.homeInterface = homeInterface;
    }

    public void loadSearching(){
        homeInterface.onClickButton();
    }
}
