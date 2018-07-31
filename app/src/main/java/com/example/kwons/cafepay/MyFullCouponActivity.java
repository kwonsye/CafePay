package com.example.kwons.cafepay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyFullCouponActivity extends AppCompatActivity {
    private ListView listView;
    private FullCouponAdapter adapter;
    private List<FullCoupon> myFullCouponList;

    Retrofit retrofit;
    CouponService couponService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_full_coupon);

        listView=(ListView)findViewById(R.id.myFullCouponListView);
        myFullCouponList=new ArrayList<FullCoupon>();

        //디비연동전 예시데이터
        //해당 카페의 쿠폰 개수받아서 개수만큼 for문 돌리기
        myFullCouponList.add(new FullCoupon("스타벅스"));
        myFullCouponList.add(new FullCoupon("탐앤탐스"));
        myFullCouponList.add(new FullCoupon("엔젤리너스"));

        //서버연결 후

        retrofit=new Retrofit.Builder()
                .baseUrl(CouponService.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        couponService=retrofit.create(CouponService.class);
        //사용자아이디 받기
        Intent intent = getIntent();
        final String userId = intent.getExtras().getString("userId");




        adapter=new FullCouponAdapter(getApplicationContext(),myFullCouponList);
        listView.setAdapter(adapter);

    }
}
