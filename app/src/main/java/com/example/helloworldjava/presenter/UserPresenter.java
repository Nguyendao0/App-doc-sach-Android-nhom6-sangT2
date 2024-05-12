package com.example.helloworldjava.presenter;

import com.example.helloworldjava.model.ProfileFeature;
import com.example.helloworldjava.model.ProfileFeatureModel;
import com.example.helloworldjava.model.UserModel;
import com.example.helloworldjava.model.entity.NguoiDung;
import com.example.helloworldjava.model.entity.User;
import com.example.helloworldjava.services.NguoiDungService;
import com.example.helloworldjava.services.ServiceBuilder;
import com.example.helloworldjava.view.user.UserView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserPresenter {
    private final ProfileFeatureModel profileFeatureModel;
    private final UserModel userModel;
    private final UserView userView;
    private NguoiDungService nguoiDungService;

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
        String userId = "fYw3HzyQGqdME6zPzDF6YSXPtPu1";
        nguoiDungService = ServiceBuilder.buildService(NguoiDungService.class);
        nguoiDungService.getNguoiDungById(userId).enqueue(new Callback<NguoiDung>() {
            @Override
            public void onResponse(Call<NguoiDung> call, Response<NguoiDung> response) {
                if (response.isSuccessful()) {
                    NguoiDung nguoiDung = response.body();
                    userView.displayUserInformation(nguoiDung);
                }
            }

            @Override
            public void onFailure(Call<NguoiDung> call, Throwable throwable) {

            }
        });
    }
}
