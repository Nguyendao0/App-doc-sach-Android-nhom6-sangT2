package com.example.helloworldjava.Services;

import com.example.helloworldjava.model.entity.User;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserService {
    @GET("nguoi-dung/{id}")
    Call<User> getNguoiDungById(@Path("id") String id);
}
