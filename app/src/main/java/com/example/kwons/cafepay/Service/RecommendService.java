package com.example.kwons.cafepay.Service;

import com.example.kwons.cafepay.Recommend;
import com.example.kwons.cafepay.TumblerInfo;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RecommendService {

    @GET("recommend/{userId}")
    Call<List<Recommend>> recommend(@Path("userId") String userId);

}
