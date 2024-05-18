package com.example.helloworldjava.view.Library;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.helloworldjava.LibraryContractInterface.CurrentReadingContract;
import com.example.helloworldjava.LibraryContractInterface.EditPopupContract;
import com.example.helloworldjava.LibraryContractInterface.LibraryContract;
import com.example.helloworldjava.LibraryContractInterface.NavigationContract;
import com.example.helloworldjava.R;
import com.example.helloworldjava.model.Realm.Sach;
import com.example.helloworldjava.presenter.Library.CurrentReadingPresenter;
import com.example.helloworldjava.presenter.Library.EditPopupPresenter;
import com.example.helloworldjava.presenter.Library.LibraryPresenter;
import com.example.helloworldjava.presenter.Library.NavigationPresenter;

import java.util.ArrayList;

public class LibraryFragment extends Fragment implements LibraryContract.View {


    private LibraryContract.Presenter presenter;
    private NavigationContract.View navigationFragment;
    private EditPopupContract.View editPopupFragment;
    private CurrentReadingContract.View curReadingFragment;

    private CurrentReadingContract.Presenter currentReadingPresenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.library_fragment, container, false);

        navigationFragment = new NavigationFragment();
        editPopupFragment = new EditPopupFragment();
        curReadingFragment = new CurrentReadingFragment();


        presenter = new LibraryPresenter();
        presenter.setLibraryView(this);
        presenter.setEditPopupView(editPopupFragment);
        presenter.setNavigationView(navigationFragment);
        setPresenter(presenter);

        NavigationContract.Presenter navigationPresenter = new NavigationPresenter();
        navigationPresenter.setNavigationView(navigationFragment);
        navigationFragment.setPresenter(navigationPresenter);
        navigationFragment.setLibraryPresenter(presenter);

        EditPopupContract.Presenter editPopupPresenter = new EditPopupPresenter();
        editPopupPresenter.setEditPopupView(editPopupFragment);
        editPopupFragment.setPresenter(editPopupPresenter);
        editPopupFragment.setLibraryPresenter(presenter);

        currentReadingPresenter = new CurrentReadingPresenter(getContext(), curReadingFragment);
        curReadingFragment.setLibraryPresenter(presenter);
        curReadingFragment.setCurrentPresenter(currentReadingPresenter);

        initUI();

        return view;
    }



    private void isEditPopupOpen(EditPopupContract.Presenter editPopupPresenter)
    {
        if(editPopupPresenter.checkVisible())
        {
            showFragmentInNavigationContainer((Fragment) navigationFragment);
        }
    }

    private void initUI()
    {
        getFragmentManager().
                beginTransaction().
                add(R.id.FCV_Navigation_Library, (Fragment) navigationFragment).
                commit();
        getFragmentManager().
                beginTransaction().
                add(R.id.FCV_Content_Library, (Fragment) curReadingFragment).
                commit();
    }

    @Override
    public void showFragmentInNavigationContainer(Fragment fragment) {
        getFragmentManager().
                beginTransaction().
                replace(R.id.FCV_Navigation_Library, fragment).
                commit();
    }


    @Override
    public void removeBooksLibrary() {
        ArrayList<String> list = new ArrayList<>();
        for(LibraryBookViewHolder bookViewHolder: presenter.getSelectBookItem())
        {
            if(bookViewHolder.getIsDowloaded() == false)
            {
                list.add(bookViewHolder.getSach().getID());
            }
        }
        if(list!=null)
        {
            currentReadingPresenter.removeBooksLibrary(list);
        }

    }


    @Override
    public void addBookOffline(Sach sach) {
        currentReadingPresenter.addBookOffline(sach, getContext());
    }

    @Override
    public void addBooksOffline() {

        ArrayList<Sach> list = new ArrayList<>();
        for(LibraryBookViewHolder bookViewHolder: presenter.getSelectBookItem())
        {
            list.add(bookViewHolder.getSach());
        }
        currentReadingPresenter.addBooksOffline(list, getContext());
    }

    @Override
    public void removeBooksOffline() {
        ArrayList<String> list = new ArrayList<>();
        for(LibraryBookViewHolder bookViewHolder: presenter.getSelectBookItem())
        {
            if(bookViewHolder.getIsDowloaded() == true)
            {
                list.add(bookViewHolder.getSach().getID());
            }
        }
        if(list!=null)
        {
            currentReadingPresenter.removesBookOffline(list);
        }
    }

    @Override
    public void removeBookOffline(String IDSach) {
        currentReadingPresenter.removeBookOffline(IDSach);
    }

    @Override
    public boolean isCurreadingVisible() {
        return curReadingFragment.Visible();
    }

    @Override
    public void initData() {
        if (currentReadingPresenter != null){
            currentReadingPresenter.readSachOffline();
        currentReadingPresenter.readSach();
    }
    }

    private void setPresenter(LibraryContract.Presenter presenter) {
        this.presenter = presenter;
    }


}