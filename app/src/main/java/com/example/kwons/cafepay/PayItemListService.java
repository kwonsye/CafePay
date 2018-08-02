package com.example.kwons.cafepay;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PayItemListService {

    public static final String API_URL="http://phil6641.cafe24.com:9287/";

    //1.결제내역 조회
    @GET("payments/{userId}")
    Call<PayItemList> getPayItemList(@Path("userId") String userId);

}
