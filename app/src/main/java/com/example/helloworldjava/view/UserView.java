package com.example.helloworldjava.view;

import com.example.helloworldjava.model.ProfileFeature;
import com.example.helloworldjava.model.entity.User;

import java.util.List;

public interface UserView {
    public void showListProfileFeatures(List<ProfileFeature> profileFeatures);
    public void displayUserInformation(User user);
}
