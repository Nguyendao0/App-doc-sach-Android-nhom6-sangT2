package com.example.helloworldjava.services.API;

import com.example.helloworldjava.model.APIEntities.Book;
import com.example.helloworldjava.model.APIEntities.Chaper;
import com.example.helloworldjava.model.APIResponeModel.ApiResponseSachModle;
import com.example.helloworldjava.Config.ConfigData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BookService {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    BookService api = new Retrofit.Builder()
            .baseUrl(ConfigData.Domain)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(BookService.class);
    @GET("api/nguoi-dung/{iduser}/thu-vien-sach")
    Call<List<ApiResponseSachModle>> ListBook(@Path("iduser")String iduser);
    @POST("api/nguoi-dung/{iduser}/thu-vien-sach/{idBook}")
    Call<String> ListBookById(@Path("idBook")String idBook);
    @POST("api/sach")
    Call<String> AddDataBookBy(@Body Book book);
    @GET("api/sach/{id}/chuong")
    Call<String> GetChaperByBook(@Path("id")String idBook);
    @POST("api/sach/{id}/chuong")
    Call<String> AddChaperByBook(@Path("id")String idBook);
    @GET("api/api/sach/{id}/chuong/findnal")
    Call<String> GetDataChaperByBook();
    @GET("api/chuong/{id}")
    Call<String> GetChaper(@Path("id")String idChaper);
    @POST("api/chuong/{id}")
    Call<String> AddChaper(@Path("id")String id,@Body Chaper chaper);
    @DELETE("api/chuong/{id}")
    Call<String> DeleteChaper(@Path("id")String id);
}
