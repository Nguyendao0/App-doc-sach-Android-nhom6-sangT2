package com.example.helloworldjava.view;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworldjava.R;
import com.example.helloworldjava.model.ProfileFeature;
import com.example.helloworldjava.model.entity.User;
import com.example.helloworldjava.presenter.UserPresenter;

import java.util.List;
import java.util.Objects;

public class UserActivity extends AppCompatActivity implements UserView {
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




        UserPresenter userPresenter = new UserPresenter(this);
        userPresenter.showListProfileFeatures();
        userPresenter.displayUserInformation();

    }


    @Override
    public void showListProfileFeatures(List<ProfileFeature> profileFeatures) {
        recyclerView = findViewById(R.id.listFeature);
        profileFeaturesAdapter = new ProfileFeaturesAdapter(this, profileFeatures);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(profileFeaturesAdapter);
    }

    @Override
    public void displayUserInformation(User user) {
        TextView usernameTextView = findViewById(R.id.username);
        usernameTextView.setText(user.getUsername());

        ImageView userAvatarImageView = findViewById(R.id.userAvatarImage);
        int resID = getResources().getIdentifier(user.getAvatar() , "drawable", getPackageName());
        userAvatarImageView.setImageDrawable(AppCompatResources.getDrawable(this, resID));
        userAvatarImageView.setBackground(AppCompatResources.getDrawable(this, R.drawable.circle_outline));
    }
}
