package com.example.helloworldjava.presenter.Library;

import androidx.fragment.app.Fragment;


import com.example.helloworldjava.LibraryContractInterface.EditPopupContract;
import com.example.helloworldjava.LibraryContractInterface.LibraryContract;
import com.example.helloworldjava.LibraryContractInterface.NavigationContract;
import com.example.helloworldjava.model.Realm.Sach;
import com.example.helloworldjava.view.Library.LibraryBookViewHolder;

import java.util.ArrayList;

public class LibraryPresenter implements LibraryContract.Presenter {
    private LibraryContract.View libraryView;
    private NavigationContract.View navigationFragment;
    private EditPopupContract.View editPopupFragment;
    private ArrayList<LibraryBookViewHolder> selectedBookViewHolders;

    public LibraryPresenter() {
        selectedBookViewHolders = new ArrayList<>();
    }

    @Override
    public void setNavigationView(NavigationContract.View navigationFragment) {
        this.navigationFragment = navigationFragment;
    }

    @Override
    public void setEditPopupView(EditPopupContract.View editPopupFragment) {
        this.editPopupFragment = editPopupFragment;
    }

    @Override
    public void setLibraryView(LibraryContract.View libraryView) {
        this.libraryView = libraryView;
    }

    @Override
    public void replaceFragmentInNavigationContainer(String nameFragment) {
        switch (nameFragment){
            case "NavigationFragment":
                libraryView.showFragmentInNavigationContainer((Fragment) navigationFragment);
                break;
            case "EditPopupFragment":
                libraryView.showFragmentInNavigationContainer((Fragment) editPopupFragment);
                break;
            default:
                break;
        }
    }

    @Override
    public int getTabSelected() {
        return libraryView.tabSelected();
    }

    @Override
    public void addToListBookView(LibraryBookViewHolder libraryBookViewHolder) {
        this.selectedBookViewHolders.add(libraryBookViewHolder);
    }

    @Override
    public void removeToListBookView(LibraryBookViewHolder libraryBookViewHolder) {
        this.selectedBookViewHolders.remove(libraryBookViewHolder);
    }

    @Override
    public boolean checkEditVisible() {
        return editPopupFragment.visible();
    }

    @Override
    public void clearSelectBookView() {
        for (LibraryBookViewHolder bookViewHolder: selectedBookViewHolders)
        {
            bookViewHolder.onClickItem(true);
        }
        this.selectedBookViewHolders.clear();
    }

    @Override
    public void selectBookItem(LibraryBookViewHolder bookViewHolder, boolean selected) {
        bookViewHolder.onClickItem(selected);
    }

    @Override
    public void addBookOffline(Sach sach) {
        libraryView.addBookOffline(sach);
    }

    @Override
    public void removeBookOffline(String IDSach) {
        libraryView.removeBookOffline(IDSach);
    }

    @Override
    public boolean isCurreadingVisible() {
        return libraryView.isCurreadingVisible();
    }


}