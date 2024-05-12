package com.example.helloworldjava.services;

import com.example.helloworldjava.model.entity.NguoiDung;
import com.example.helloworldjava.model.Realm.ThuVienSachCaNhan;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface NguoiDungService {
    @GET("nguoi-dung/{id}")
    Call<NguoiDung> getNguoiDungById(@Path("id") String id);

    @POST("nguoi-dung/")
    Call<NguoiDung> createNewNguoiDung(@Body() NguoiDung newNguoiDung);

    @FormUrlEncoded
    @POST("nguoi-dung/login")
    Call<NguoiDung> loginByPasswordAndEmail(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("nguoi-dung/login/google")
    Call<NguoiDung> loginByGoogleId(@Field("googleId") String googleId);

    @GET("/api/nguoi-dung/{idNguoiDung}/thu-vien-sach")
    Call<List<ThuVienSachCaNhan>> getListSachTrongThuVien(@Path("idNguoiDung") String idNguoiDung);
}