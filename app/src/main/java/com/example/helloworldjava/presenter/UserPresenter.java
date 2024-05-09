package com.example.helloworldjava.presenter;

import com.example.helloworldjava.model.ProfileFeature;
import com.example.helloworldjava.model.ProfileFeatureModel;
import com.example.helloworldjava.model.UserModel;
import com.example.helloworldjava.model.entity.User;
import com.example.helloworldjava.view.user.UserView;

import java.util.List;

public class UserPresenter {
    private final ProfileFeatureModel profileFeatureModel;
    private final UserModel userModel;
    private final UserView userView;

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
        String userId = "3lxkrwmfj4qEV9WowcSX";
        userModel.getUserById(userId, new UserModel.GetUserCallback() {
            @Override
            public void onUserLoaded(User user) {
                userView.displayUserInformation(user);
            }

            @Override
            public void onError(Exception e) {

            }
        });

    }
}
