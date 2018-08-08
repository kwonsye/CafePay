package com.example.kwons.cafepay.Service;

import com.example.kwons.cafepay.Stamp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CouponService {

    //1.카페이름별 스탬프개수 조회
    @GET("coupons/{userId}/count")
    Call<Stamp> getStampCount(@Path("userId") String userId);

    //2.스탬프리스트 조회
    @GET("coupons/{userId}")
    Call<List<Stamp>>getStampList(@Path("userId") String userId);

    //3.결제후 스탬프 등록,추가
    @POST("coupons/{userId}")
    Call<Stamp>registStamp(@Path("userId") String userId, @Body String CafeName);

    //2.카페에서 카페이름별 다 채워진 쿠폰 사용(-)==>아이디하고 카페이름하고 put하면/쿠폰 사용이 되면 스탬프개수 10개 줄이는것은 백엔드에서 해주시는 건지?
    @PUT("coupons/{userId}/{cafeName}")
    Call<Stamp>putUpdateCoupon(@Path("userId") String userId, @Path("cafeName") String cafeName);


}
