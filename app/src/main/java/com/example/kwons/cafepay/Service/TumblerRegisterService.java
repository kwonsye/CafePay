package com.example.kwons.cafepay.Service;

import com.example.kwons.cafepay.TumblerInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TumblerRegisterService {

    @POST("tumblers")
    Call<JSONObject> registerTumblerSerialNumber(@Body JSONObject registerTumbler);


    //1.결제내역 조회
    @GET("tumblers/{userId}")
    Call<List<TumblerInfo>> getTumblerById(@Path("userId") String userId);

}
