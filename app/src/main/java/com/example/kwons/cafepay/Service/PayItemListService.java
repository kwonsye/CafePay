package com.example.kwons.cafepay.Service;

import com.example.kwons.cafepay.PayItemList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PayItemListService {
    //1.결제내역 조회
    @GET("payments/{userId}")
    Call<PayItemList> getPayItemList(@Path("userId") String userId);

}
