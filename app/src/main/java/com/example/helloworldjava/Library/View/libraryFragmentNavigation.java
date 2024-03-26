package com.example.helloworldjava.Library.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.helloworldjava.Library.LibraryActivity;
import com.example.helloworldjava.Library.QuestionActivity;
import com.example.helloworldjava.R;
import com.google.android.material.tabs.TabLayout;


public class libraryFragmentNavigation extends Fragment {
    private LibraryInterface.View mView;
    private PopupMenu popupMenu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.library_fragment_navigation, container, false);
        mView = (LibraryInterface.View) getActivity();

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
                            getActivity().getSupportFragmentManager().beginTransaction()
                                    .setReorderingAllowed(true)
                                    .add(R.id.fragmentContainerViewNavigation, mView.returnF(), null)
                                    .addToBackStack(null)
                                    .commit();
                            return true;
                        } else if (itemId == R.id.viewModeMenuItem) {
                            Toast.makeText(view.getContext(), "Chế độ xem", Toast.LENGTH_SHORT).show();
                            return true;
                        } else if (itemId == R.id.titleMenuItem) {
                            Toast.makeText(view.getContext(), "Tiêu đề", Toast.LENGTH_SHORT).show();
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TabLayout tabLayout = getActivity().findViewById(R.id.tabLayoutLibrary);

        int position = tabLayout.getSelectedTabPosition(); System.out.println(position);
        switch (position){
            case 1:
                popupMenu.getMenu().findItem(R.id.sortMenuItem).getSubMenu().removeItem(R.id.readRecentlyMenuItem);
                popupMenu.getMenu().findItem(R.id.sortMenuItem).getSubMenu().removeItem(R.id.updateRecentlyMenuItem);
                popupMenu.getMenu().findItem(R.id.sortMenuItem).getSubMenu().removeItem(R.id.addRecentlyMenuItem);
                break;
            case 2:
                popupMenu.getMenu().removeItem(R.id.sortMenuItem);
                popupMenu.getMenu().removeItem(R.id.viewModeMenuItem);
                break;
            default:
                break;
        }
    }


}
