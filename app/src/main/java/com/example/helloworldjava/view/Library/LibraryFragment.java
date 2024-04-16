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
import com.example.helloworldjava.LibraryContractInterface.ReadingListContract;
import com.example.helloworldjava.LibraryContractInterface.StorageContract;
import com.example.helloworldjava.R;
import com.example.helloworldjava.presenter.Library.EditPopupPresenter;
import com.example.helloworldjava.presenter.Library.LibraryPresenter;
import com.example.helloworldjava.presenter.Library.NavigationPresenter;
import com.google.android.material.tabs.TabLayout;

public class LibraryFragment extends Fragment implements LibraryContract.View {

    private TabLayout tabLayout;
    private LibraryContract.Presenter presenter;
    private NavigationContract.View navigationFragment;
    private EditPopupContract.View editPopupFragment;
    private CurrentReadingContract.View curReadingFragment;
    private StorageContract.View storageFragment;
    private ReadingListContract.View readListFragment;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.library_fragment, container, false);

        navigationFragment = new NavigationFragment();
        editPopupFragment = new EditPopupFragment();
        curReadingFragment = new CurrentReadingFragment();
        storageFragment = new StorageFragment();
        readListFragment = new ReadingListFragment();

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

        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout_Library);
        initUI();
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                navigationPresenter.updateLibraryMenu(position);

                isEditPopupOpen(editPopupPresenter);

                switch (position)
                {
                    case 0:
                        replaceFragmentInContentContainer((Fragment) curReadingFragment);
                        break;
                    case 1:
                        replaceFragmentInContentContainer((Fragment) storageFragment);
                        break;
                    case 2:
                        replaceFragmentInContentContainer((Fragment) readListFragment);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }

    private void isEditPopupOpen(EditPopupContract.Presenter editPopupPresenter)
    {
        if(editPopupPresenter.checkVisible())
        {
            showFragmentInNavigationContainer((Fragment) navigationFragment);
        }
    }

    private void replaceFragmentInContentContainer(Fragment fragment)
    {
        getFragmentManager().
                beginTransaction().
                replace(R.id.FCV_Content_Library, fragment).
                commit();
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
    public int tabSelected() {
        return tabLayout.getSelectedTabPosition();
    }

    private void setPresenter(LibraryContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
