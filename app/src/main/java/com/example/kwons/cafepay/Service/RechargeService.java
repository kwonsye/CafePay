package com.example.kwons.cafepay.Service;

import com.example.kwons.cafepay.RechargeInfo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RechargeService {
    //1.충전하기
    @PUT("points/{userId}/charge")
    Call<RechargeInfo> recharge(@Path("userId") String userId, @Body Integer chargePoint);

}
