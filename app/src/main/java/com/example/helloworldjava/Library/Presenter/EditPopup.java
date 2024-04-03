package com.example.helloworldjava.Library.Presenter;

import androidx.fragment.app.Fragment;

import com.example.helloworldjava.Library.LibraryInterface.CurrentReadingContract;
import com.example.helloworldjava.Library.LibraryInterface.EditPopupContract;
import com.example.helloworldjava.Library.LibraryInterface.LibraryContract;
import com.example.helloworldjava.Library.LibraryInterface.LibraryNavigationContract;

public class EditPopup implements EditPopupContract.Presenter {
    private LibraryContract.View libraryView;
    private LibraryNavigationContract.View navigationFragment;
    private EditPopupContract.View editPopupView;
    private CurrentReadingContract.View currentFragment;
    public EditPopup(EditPopupContract.View editPopupView, LibraryNavigationContract.View navigationFragment, LibraryContract.View libraryView, CurrentReadingContract.View currentFragment) {
        this.libraryView = libraryView;
        this.navigationFragment = navigationFragment;
        this.editPopupView = editPopupView;
        this.currentFragment = currentFragment;
    }

    @Override
    public void showNavigationFragment(int containerViewId) {
        libraryView.replaceFragmentToFragmentContainerView((Fragment) navigationFragment, containerViewId);
    }

    @Override
    public void resetItemViewSelected()
    {
        currentFragment.getItemViemSelected();
    }
}
