package com.example.helloworldjava.services;

import com.example.helloworldjava.model.Realm.Chuong;
import com.example.helloworldjava.model.entity.TheLoaiSach;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TheLoaiService {
    @GET("the-loai-sach")
    Call<List<TheLoaiSach>> getListTheLoai();
}
