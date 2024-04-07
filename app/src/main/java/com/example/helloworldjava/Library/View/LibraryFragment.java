package com.example.helloworldjava.Library.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import com.example.helloworldjava.Library.LibraryInterface.CurrentReadingContract;
import com.example.helloworldjava.Library.LibraryInterface.EditPopupContract;
import com.example.helloworldjava.Library.LibraryInterface.LibraryContract;
import com.example.helloworldjava.Library.LibraryInterface.LibraryNavigationContract;
import com.example.helloworldjava.Library.Presenter.CurrentReading;
import com.example.helloworldjava.Library.Presenter.EditPopup;
import com.example.helloworldjava.Library.Presenter.LibraryNavigation;
import com.example.helloworldjava.R;
import com.google.android.material.tabs.TabLayout;

public class LibraryFragment extends Fragment implements LibraryContract.View {

    private EditPopupContract.View editPopupFragment;
    private LibraryNavigationContract.View navigationFragment;
    private CurrentReadingContract.View currentReadingFragment;
    private ReadingListFragment readingListFragment;
    private BookStorageFragment bookStorageFragment;
    private TabLayout tabLayout;
    private FragmentContainerView fcv;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.library_fragment, container, false);

        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout_Library);
        fcv = view.findViewById(R.id.FCV_Navigation_Library);


        editPopupFragment = new LibraryEditPopupFragment();
        navigationFragment = new LibraryNavigationFragment();
        currentReadingFragment = new CurrentReadingFragment();
        readingListFragment = new ReadingListFragment();
        bookStorageFragment = new BookStorageFragment();

        //Navigation Fragment
        LibraryNavigationContract.Presenter navigationPresenter = new LibraryNavigation(navigationFragment,editPopupFragment, this);
        navigationFragment.setNavigationPresenter(navigationPresenter);
        getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.FCV_Navigation_Library, (Fragment) navigationFragment)
                .commit();
        //Navigation Fragment

        //EditPopupFragment
        EditPopupContract.Presenter editPopupPresenter = new EditPopup(editPopupFragment ,navigationFragment, this, currentReadingFragment);
        editPopupFragment.setEditPopupPresenter(editPopupPresenter);

        //EditPopupFragment

        //CurrentReadingFragment
        CurrentReadingContract.Presenter currentReadingPresenter = new CurrentReading(currentReadingFragment, editPopupFragment);
        currentReadingFragment.setCurrentReadingPresenter(currentReadingPresenter);
        getActivity().getSupportFragmentManager().
                beginTransaction().
                add(R.id.FCV_Content_Library, (Fragment) currentReadingFragment).
                commit();
        //CurrentReadingFragment

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                navigationPresenter.onClickTabItem(position, R.id.FCV_Navigation_Library);

                switch (position)
                {
                    case 0:
                        replaceFragmentToFragmentContainerView((Fragment) currentReadingFragment, R.id.FCV_Content_Library);
                        break;
                    case 1:
                       replaceFragmentToFragmentContainerView((Fragment) bookStorageFragment, R.id.FCV_Content_Library);
                        break;
                    case 2:
                        replaceFragmentToFragmentContainerView((Fragment) readingListFragment, R.id.FCV_Content_Library);
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



    @Override
    public void replaceFragmentToFragmentContainerView(Fragment fragment, int containerView) {
        getActivity().getSupportFragmentManager().
                beginTransaction().
                replace(containerView, fragment).
                commit();
    }

    @Override
    public int getTabSelected() {
        return tabLayout.getSelectedTabPosition();
    }
}
