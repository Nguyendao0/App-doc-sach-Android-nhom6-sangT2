package com.example.helloworldjava.Library.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.helloworldjava.Library.LibraryInterface.LibraryNavigationContract;
import com.example.helloworldjava.R;


public class LibraryNavigationFragment extends Fragment implements LibraryNavigationContract.View{

    private PopupMenu popupMenu;
    private LibraryNavigationContract.Presenter navigationPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.library_fragment_navigation, container, false);


        ImageButton button = view.findViewById(R.id.imageButtonMore);

        ImageButton questionButton = view.findViewById(R.id.imageButtonQuestion);

        popupMenu = new PopupMenu(view.getContext(), button);
        popupMenu.getMenuInflater().inflate(R.menu.library_menu, popupMenu.getMenu());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int itemId = item.getItemId();

                        if (itemId == R.id.editMenuItem) {
                            navigationPresenter.showEditPopupFragment(R.id.FCV_Navigation_Library);
                            return true;
                        } else if (itemId == R.id.viewModeMenuItem) {
                            Toast.makeText(view.getContext(), "Chế độ xem", Toast.LENGTH_SHORT).show();
                            return true;
                        } else if (itemId == R.id.titleMenuItem) {
                            Toast.makeText(view.getContext(), "Tiêu đề", Toast.LENGTH_SHORT).show();
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
    public void updatePopupmenu(int position)
    {
        switch (position) {
            case 1:
                popupMenu.getMenu().findItem(R.id.readRecentlyMenuItem).setVisible(false);
                popupMenu.getMenu().findItem(R.id.updateRecentlyMenuItem).setVisible(false);
                popupMenu.getMenu().findItem(R.id.addRecentlyMenuItem).setVisible(false);
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
    public void resetPopupMenu()
    {
        popupMenu.getMenu().findItem(R.id.sortMenuItem).setVisible(true);
        popupMenu.getMenu().findItem(R.id.viewModeMenuItem).setVisible(true);
        popupMenu.getMenu().findItem(R.id.readRecentlyMenuItem).setVisible(true);
        popupMenu.getMenu().findItem(R.id.updateRecentlyMenuItem).setVisible(true);
        popupMenu.getMenu().findItem(R.id.addRecentlyMenuItem).setVisible(true);
    }



    public void setNavigationPresenter(LibraryNavigationContract.Presenter navigationPresenter) {
        this.navigationPresenter = navigationPresenter;
    }
}
