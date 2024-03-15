package com.example.helloworldjava;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserActivity extends AppCompatActivity {
    private List<ProfileFeature> profileFeatures;
    private RecyclerView recyclerView;
    private ProfileFeaturesAdapter profileFeaturesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        ImageView userAvatarImageView = findViewById(R.id.userAvatarImage);
        Glide.with(this).load("https://i.pravatar.cc/150?u=fake@pravatar.com")
                .circleCrop()
                .into(userAvatarImageView);

        recyclerView = findViewById(R.id.listFeature);

        profileFeatures = new ArrayList<>();
        profileFeatures.add(new ProfileFeature(R.drawable.notifications, "Thông báo"));
        profileFeatures.add(new ProfileFeature(R.drawable.bookmark, "Danh sách yêu thích"));
        profileFeatures.add(new ProfileFeature(R.drawable.settings_24px, "Thông tin tài khoản", AccountSettingActivity.class));
        profileFeatures.add(new ProfileFeature(R.drawable.logout, "Đăng xuất"));

        profileFeaturesAdapter = new ProfileFeaturesAdapter(this, profileFeatures);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(profileFeaturesAdapter);


    }


}
