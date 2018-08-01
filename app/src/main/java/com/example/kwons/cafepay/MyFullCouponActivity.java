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

        //서버연결 후

        retrofit=new Retrofit.Builder()
                .baseUrl(CouponService.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        couponService=retrofit.create(CouponService.class);

        //사용자아이디 받기
        Intent intent = getIntent();
        final String userId = intent.getExtras().getString("userId");

        //사용자의 쿠폰개수 받기
        int myStarbucksCouponCount=intent.getExtras().getInt("myStarbucksCoupon");
        int myEdiyaCouponCount=intent.getExtras().getInt("myEdiyaCoupon");
        int myHollysCouponCount=intent.getExtras().getInt("myHollysCoupon");
        int myAngelinusCouponCount=intent.getExtras().getInt("myAngelinusCoupon");
        int myTomnTomsCouponCount=intent.getExtras().getInt("myTomnTomsCoupon");

        for(int couponCount=1;couponCount<=myStarbucksCouponCount;couponCount++){
            myFullCouponList.add(new FullCoupon("스타벅스"));
        }

        for(int couponCount=1;couponCount<=myEdiyaCouponCount;couponCount++){
            myFullCouponList.add(new FullCoupon("이디야"));
        }

        for(int couponCount=1;couponCount<=myHollysCouponCount;couponCount++){
            myFullCouponList.add(new FullCoupon("할리스"));
        }

        for(int couponCount=1;couponCount<=myAngelinusCouponCount;couponCount++){
            myFullCouponList.add(new FullCoupon("엔젤리너스"));
        }

        for(int couponCount=1;couponCount<=myTomnTomsCouponCount;couponCount++){
            myFullCouponList.add(new FullCoupon("탐앤탐스"));
        }



        adapter=new FullCouponAdapter(getApplicationContext(),myFullCouponList);
        listView.setAdapter(adapter);

    }
}
