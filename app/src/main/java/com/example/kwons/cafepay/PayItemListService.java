package com.example.kwons.cafepay;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PayItemListService {

    public static final String API_URL="http://phil6641.cafe24.com:9287/";

    //1.결제하기
    //Call <'주고받을 객체'> '함수명' (@Query ('변수 이름') '자료형' '변수 이름')
    @GET("payments")
    Call<PayItem> payValue();

    //2.결제내역 조회
    @GET("payments/{userId}")
    Call<PayItemList> getPayItemList(@Path("userId") String userId);

}
