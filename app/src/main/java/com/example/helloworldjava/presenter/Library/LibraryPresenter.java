package com.example.helloworldjava.presenter.Library;

import androidx.fragment.app.Fragment;

import com.example.helloworldjava.LibraryContractInterface.EditPopupContract;
import com.example.helloworldjava.LibraryContractInterface.LibraryContract;
import com.example.helloworldjava.LibraryContractInterface.NavigationContract;

public class LibraryPresenter implements LibraryContract.Presenter {
    private LibraryContract.View libraryView;
    private NavigationContract.View navigationFragment;
    private EditPopupContract.View editPopupFragment;

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
}
