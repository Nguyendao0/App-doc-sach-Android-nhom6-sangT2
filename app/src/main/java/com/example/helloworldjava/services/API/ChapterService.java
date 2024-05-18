package com.example.helloworldjava.services.API;

import com.example.helloworldjava.model.APIEntities.ModelString;
import com.example.helloworldjava.Config.ConfigData;
import com.example.helloworldjava.model.entity.Sach;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ChapterService {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    ChapterService api = new Retrofit.Builder()
            .baseUrl(ConfigData.IP + "api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ChapterService.class);
    @GET("nguoi-dung/{id}/thu-vien-sach")
    Call<List<Sach>>ListScach(@Path("id")String ID);
    @PUT("?")
    Call<ModelString> EditChapter(@Body ModelString modelString);
    @GET("?")
    Call<ModelString> GetChapter(@Body ModelString modelString);
    @DELETE("?")
    Call<ModelString> DeleteChapter(@Body ModelString modelString);
}
