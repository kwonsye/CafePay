package com.example.kwons.cafepay;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RechargeService {

    public static final String API_URL="http://phil6641.cafe24.com:9287/";

    //1.충전하기
    @PUT("points/{userId}/charge")
    Call<RechargeInfo> recharge(@Path("userId") String userId, @Body Integer chargePoint);

}
