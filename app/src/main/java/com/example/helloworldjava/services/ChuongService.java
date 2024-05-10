package com.example.helloworldjava.services;

import com.example.helloworldjava.model.Realm.Chuong;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ChuongService {
    @GET("`chuong`")
    Call<List<Chuong>> getListChuongg();

    @GET("chuong/{id}")
    Call<Chuong> getChuong(@Path("id")String id);

    @GET("sach/{idSach}/chuong")
    Call<List<Chuong>> getListChuong(@Path("idSach") String idSach);

}
