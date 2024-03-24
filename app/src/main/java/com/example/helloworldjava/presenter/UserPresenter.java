package com.example.helloworldjava.presenter;

import com.example.helloworldjava.model.ProfileFeature;
import com.example.helloworldjava.model.ProfileFeatureModel;
import com.example.helloworldjava.model.UserModel;
import com.example.helloworldjava.model.entity.User;
import com.example.helloworldjava.view.UserView;

import java.util.List;

public class UserPresenter {
    private ProfileFeatureModel profileFeatureModel;
    private UserModel userModel;
    private UserView userView;

    public UserPresenter(UserView userView) {
        this.profileFeatureModel = new ProfileFeatureModel();
        this.userModel = new UserModel();
        this.userView = userView;
    }

    public void showListProfileFeatures() {
        List<ProfileFeature> profileFeatures = profileFeatureModel.getListFeatures();
        userView.showListProfileFeatures(profileFeatures);
    }

    public void displayUserInformation() {
        User user = userModel.getUser();
        userView.displayUserInformation(user);
    }
}