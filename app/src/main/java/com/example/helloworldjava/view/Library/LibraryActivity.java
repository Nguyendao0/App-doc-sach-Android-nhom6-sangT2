package com.example.helloworldjava.view.Library;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.helloworldjava.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class LibraryActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    private TabLayout mTabLayout;
    private ViewPager2 viewPager2;
    private ViewPagerAdapter VPA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        mTabLayout = findViewById(R.id.topMenu);
        viewPager2 = findViewById(R.id.VPA);


        VPA = new ViewPagerAdapter(this);
        viewPager2.setAdapter(VPA);

        new TabLayoutMediator(mTabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

                switch (position)
                {
                    case 0 :
                        tab.setText("TRUYỆN ĐANG ĐỌC");
                         break;
                    case 1 :
                        tab.setText("LƯU TRỮ");
                        break;
                    case 2:
                        tab.setText("DANH SÁCH ĐỌC");
                        break;
                }
            }
        }).attach();
    }

    public void showOptions(View view)
    {
        int selectedTabPosition = mTabLayout.getSelectedTabPosition();
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.library_menu);

        switch (selectedTabPosition)
        {
            case 1 :
                popupMenu.getMenu().findItem(R.id.sortMenuItem).getSubMenu().removeItem(R.id.readRecentlyMenuItem);
                popupMenu.getMenu().findItem(R.id.sortMenuItem).getSubMenu().removeItem(R.id.updateRecentlyMenuItem);
                popupMenu.getMenu().findItem(R.id.sortMenuItem).getSubMenu().removeItem(R.id.addRecentlyMenuItem);
                break;
            case 2 :
                popupMenu.getMenu().removeItem(R.id.sortMenuItem);
                popupMenu.getMenu().removeItem(R.id.editMenuItem);
                break;
            default:
                break;
        }
        popupMenu.show();
    }

    public void showQuestion(View view)
    {
        Intent intent = new Intent(this, QuestionActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        int itemId = menuItem.getItemId();

        if (itemId == R.id.editMenuItem) {
            Toast.makeText(this, "Chỉnh sửa", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.viewModeMenuItem) {
            Toast.makeText(this, "Chế độ xem", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.titleMenuItem) {
            Toast.makeText(this, "Tiêu đề", Toast.LENGTH_SHORT).show();
        }

        return false;
    }
}