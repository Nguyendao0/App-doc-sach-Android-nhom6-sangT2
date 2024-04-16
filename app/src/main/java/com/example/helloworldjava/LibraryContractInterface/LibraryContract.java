package com.example.helloworldjava.LibraryContractInterface;

import androidx.fragment.app.Fragment;

public interface LibraryContract {
    interface View {
        void showFragmentInNavigationContainer(Fragment fragment);
        int tabSelected();
    }
    interface Presenter{
        void setNavigationView(NavigationContract.View navigationFragment);
        void setEditPopupView(EditPopupContract.View editPopupFragment);
        void setLibraryView(LibraryContract.View libraryView);
        void replaceFragmentInNavigationContainer(String nameFragment);
        int getTabSelected();
    }
}
