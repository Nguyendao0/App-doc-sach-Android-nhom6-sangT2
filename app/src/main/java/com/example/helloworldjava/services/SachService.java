package com.example.helloworldjava.services;

import com.example.helloworldjava.model.entity.Sach;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface SachService {
    @GET("sach")
    Call<List<Sach>> getListSachs();

    @GET("sach/{id}")
    Call<Sach> getSach(@Path("id")String id);

    @POST("sach")
    Call<Sach> createSach(@Body Sach newSach);

    @FormUrlEncoded
    @PUT("sach/{id}")
    Call<Sach> updateSach(
            @Path("id")String id,
            @Field("img")String img,
            @Field("TenSach")String TenSach,
            @Field("NhaXuatBan")String NhaXuatBan,
            @Field("NamXuatBan")int NamXuatBan,
            @Field("Mota")int Mota
    );

    @DELETE("sach/{id}")
    Call<Void> deleteSach(@Path("id")String id);
}
