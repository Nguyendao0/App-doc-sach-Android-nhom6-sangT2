package com.example.helloworldjava.presenter.Library;

import com.example.helloworldjava.presenter.LibraryContractInterface.NavigationContract;

public class NavigationPresenter implements NavigationContract.Presenter {
    private NavigationContract.View navigationView;
    @Override
    public void setNavigationView(NavigationContract.View navigationView){
        this.navigationView = navigationView;
    }
    @Override
    public void updateLibraryMenu(int position) {
        navigationView.showLibraryMenu(position);
    }
}