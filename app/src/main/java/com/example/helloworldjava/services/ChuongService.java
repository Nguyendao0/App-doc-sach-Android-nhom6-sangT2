package com.example.helloworldjava.services;

import com.example.helloworldjava.model.Realm.Chuong;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ChuongService {
    @GET("`chuong`")
    Call<List<Chuong>> getListChuongg();

    @GET("chuong/{id}")
    Call<Chuong> getChuong(@Path("id")String id);

    @GET("sach/{idSach}/chuong")
    Call<List<Chuong>> getListChuong(@Path("idSach") String idSach);

    @POST("sach/{idSach}/chuong")
    Call<Chuong> addChuong(
            @Path("idSach") String idSach,
            @Body Chuong chuong
    );

    @PATCH("chuong/{idChuong}")
    Call<Chuong> updateChuong(
            @Path("idChuong") String idChuong,
            @Body Chuong chuong
    );

    @DELETE("chuong/{idChuong}")
    Call<Void> deleteChuong(
            @Path("idChuong") String idChuong
    );

}
