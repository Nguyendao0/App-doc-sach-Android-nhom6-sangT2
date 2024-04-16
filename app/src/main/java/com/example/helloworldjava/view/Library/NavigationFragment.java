package com.example.helloworldjava.view.Library;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.helloworldjava.Library.View.QuestionActivity;
import com.example.helloworldjava.LibraryContractInterface.LibraryContract;
import com.example.helloworldjava.LibraryContractInterface.NavigationContract;
import com.example.helloworldjava.R;

public class NavigationFragment extends Fragment implements NavigationContract.View {
    private PopupMenu popupMenu;
    private ImageButton moreButton;
    private ImageButton questionButton;
    private NavigationContract.Presenter navigationPresenter;
    private LibraryContract.Presenter libraryPresenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.library_fragment_navigation, container, false);

        moreButton = view.findViewById(R.id.imageButtonMore);
        questionButton = view.findViewById(R.id.imageButtonQuestion);

        popupMenu = new PopupMenu(view.getContext(), moreButton);
        popupMenu.getMenuInflater().inflate(R.menu.library_menu, popupMenu.getMenu());

        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        int itemId = menuItem.getItemId();
                        if (itemId == R.id.editMenuItem) {
                            libraryPresenter.replaceFragmentInNavigationContainer("EditPopupFragment");
                            return true;
                        } else if (itemId == R.id.viewModeMenuItem) {
                            return true;
                        } else if (itemId == R.id.titleMenuItem) {
                            return true;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

        questionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), QuestionActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void showLibraryMenu(int position) {
        switch (position) {
            case 0:
                popupMenu.getMenu().findItem(R.id.readRecentlyMenuItem).setVisible(true);
                popupMenu.getMenu().findItem(R.id.updateRecentlyMenuItem).setVisible(true);
                popupMenu.getMenu().findItem(R.id.addRecentlyMenuItem).setVisible(true);
                popupMenu.getMenu().findItem(R.id.sortMenuItem).setVisible(true);
                popupMenu.getMenu().findItem(R.id.viewModeMenuItem).setVisible(true);
                break;
            case 1:
                popupMenu.getMenu().findItem(R.id.readRecentlyMenuItem).setVisible(false);
                popupMenu.getMenu().findItem(R.id.updateRecentlyMenuItem).setVisible(false);
                popupMenu.getMenu().findItem(R.id.addRecentlyMenuItem).setVisible(false);
                popupMenu.getMenu().findItem(R.id.sortMenuItem).setVisible(true);
                popupMenu.getMenu().findItem(R.id.viewModeMenuItem).setVisible(true);
                break;
            case 2:
                popupMenu.getMenu().findItem(R.id.sortMenuItem).setVisible(false);
                popupMenu.getMenu().findItem(R.id.viewModeMenuItem).setVisible(false);
                break;
            default:
                break;
        }
    }

    @Override
    public void setPresenter(NavigationContract.Presenter navigationPresenter)
    {
        this.navigationPresenter = navigationPresenter;
    }

    @Override
    public void setLibraryPresenter(LibraryContract.Presenter libraryPresenter) {
        this.libraryPresenter = libraryPresenter;
    }
}
