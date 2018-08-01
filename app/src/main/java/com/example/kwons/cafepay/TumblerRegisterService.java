package com.example.kwons.cafepay;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TumblerRegisterService {

    public static final String API_URL="http://phil6641.cafe24.com:9287/";

    @POST("tumblers")
    Call<JSONObject> registerTumblerSerialNumber(@Body JSONObject registerTumbler);

}
