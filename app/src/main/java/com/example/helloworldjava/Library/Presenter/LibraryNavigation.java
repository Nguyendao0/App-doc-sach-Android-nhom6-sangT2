package com.example.helloworldjava.Library.Presenter;

import androidx.fragment.app.Fragment;

import com.example.helloworldjava.Library.LibraryInterface.EditPopupContract;
import com.example.helloworldjava.Library.LibraryInterface.LibraryContract;
import com.example.helloworldjava.Library.LibraryInterface.LibraryNavigationContract;

public class LibraryNavigation implements LibraryNavigationContract.Presenter {

    private LibraryNavigationContract.View navigationView ;
    private EditPopupContract.View editPopupFragment;
    private LibraryContract.View libraryView;
    public LibraryNavigation(LibraryNavigationContract.View navigationView,
    EditPopupContract.View editPopupFragment, LibraryContract.View libraryView) {
        this.navigationView = navigationView;
        this.editPopupFragment = editPopupFragment;
        this.libraryView = libraryView;
    }

    @Override
    public void showEditPopupFragment(int containerViewId)
    {
        editPopupFragment.updatePopupmenu(libraryView.getTabSelected());
        libraryView.replaceFragmentToFragmentContainerView((Fragment) editPopupFragment, containerViewId);
    }

    @Override
    public void onClickTabItem(int position, int containerView) {
        libraryView.replaceFragmentToFragmentContainerView((Fragment) navigationView, containerView);
        navigationView.resetPopupMenu();
        navigationView.updatePopupmenu(position);
    }



}
