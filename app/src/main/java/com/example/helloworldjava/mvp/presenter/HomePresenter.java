package com.example.helloworldjava.mvp.presenter;

public class HomePresenter {
    private HomeInterface homeInterface;

    public HomePresenter(HomeInterface homeInterface) {
        this.homeInterface = homeInterface;
    }

    public void loadSearching(){
        homeInterface.onClick();
    }
}
