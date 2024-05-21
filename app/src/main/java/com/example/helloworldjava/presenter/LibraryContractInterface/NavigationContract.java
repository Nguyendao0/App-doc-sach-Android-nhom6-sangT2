package com.example.helloworldjava.presenter.LibraryContractInterface;

public interface NavigationContract {
    interface View{
        void showLibraryMenu(int position);
        void setPresenter(NavigationContract.Presenter navigationPresenter);
        void setLibraryPresenter(LibraryContract.Presenter libraryPresenter);
    }

    interface Presenter{
        void updateLibraryMenu(int position);
        void setNavigationView(NavigationContract.View navigationView);

    }
}