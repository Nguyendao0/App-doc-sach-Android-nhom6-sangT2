package com.example.helloworldjava.Library.View;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.helloworldjava.Library.LibraryInterface.CurrentReadingContract;
import com.example.helloworldjava.Library.LibraryInterface.EditPopupContract;
import com.example.helloworldjava.Library.LibraryInterface.LibraryContract;
import com.example.helloworldjava.Library.LibraryInterface.LibraryNavigationContract;
import com.example.helloworldjava.Library.Presenter.CurrentReading;
import com.example.helloworldjava.Library.Presenter.EditPopup;
import com.example.helloworldjava.Library.Presenter.LibraryNavigation;
import com.example.helloworldjava.R;
import com.google.android.material.tabs.TabLayout;

public class LibraryActivity extends AppCompatActivity implements LibraryContract.View {

    private EditPopupContract.View editPopupFragment;
    private LibraryNavigationContract.View navigationFragment;
    private CurrentReadingContract.View currentReadingFragment;
    private ReadingListFragment readingListFragment;
    private BookStorageFragment bookStorageFragment;

    private TabLayout tabLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);



        tabLayout = findViewById(R.id.tabLayoutLibrary);

        editPopupFragment = new LibraryEditPopupFragment();
        navigationFragment = new LibraryNavigationFragment();
        currentReadingFragment = new CurrentReadingFragment();
        readingListFragment = new ReadingListFragment();
        bookStorageFragment = new BookStorageFragment();


        //Navigation Fragment
        LibraryNavigationContract.Presenter navigationPresenter = new LibraryNavigation(navigationFragment,editPopupFragment, this);
        navigationFragment.setNavigationPresenter(navigationPresenter);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainerViewNavigation, (Fragment) navigationFragment)
                .commit();
        //Navigation Fragment

        //EditPopupFragment
        EditPopupContract.Presenter editPopupPresenter = new EditPopup(editPopupFragment ,navigationFragment, this, currentReadingFragment);
        editPopupFragment.setEditPopupPresenter(editPopupPresenter);
        //EditPopupFragment

        //CurrentReadingFragment
        CurrentReadingContract.Presenter currentReadingPresenter = new CurrentReading(currentReadingFragment, editPopupFragment);
        currentReadingFragment.setCurrentReadingPresenter(currentReadingPresenter);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainerViewLibraryContent, (Fragment) currentReadingFragment)
                .commit();
        //CurrentReadingFragment
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                navigationPresenter.onClickTabItem(position, R.id.fragmentContainerViewNavigation);
                switch (position)
                {
                    case 0:
                        replaceFragmentToFragmentContainerView((Fragment) currentReadingFragment, R.id.fragmentContainerViewLibraryContent);
                        break;
                    case 1:
//                        replaceFragmentToFragmentContainerView((Fragment) bookStorageFragment, R.id.fragmentContainerViewLibraryContent);
                        break;
                    case 2:
//                        replaceFragmentToFragmentContainerView((Fragment) readingListFragment, R.id.fragmentContainerViewLibraryContent);
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
    }


    @Override
    public void replaceFragmentToFragmentContainerView(Fragment fragment, int containerView) {
        getSupportFragmentManager().
                beginTransaction().
                replace(containerView, fragment).
                commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

