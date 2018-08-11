package com.example.kwons.cafepay.Service;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EvaluateByCafeMenuService {

    @POST("payments/evaluate/{userId}/{paymentId}")
    Call<JSONObject> evaluateByCafeMenuService(@Path("userId") String userId, @Path("paymentId") Integer paymentId, @Body Float value);

}
