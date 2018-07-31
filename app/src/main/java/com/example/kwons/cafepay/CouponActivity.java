package com.example.kwons.cafepay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CouponActivity extends AppCompatActivity {

    Retrofit retrofit;
    CouponService couponService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);

        retrofit=new Retrofit.Builder()
                .baseUrl(CouponService.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        couponService=retrofit.create(CouponService.class);

        //사용자아이디 받기
        Intent intent = getIntent();
        final String userId = intent.getExtras().getString("userId");

        //초기에는 스타벅스버튼을 누른것으로 초기화

        //스타벅스버튼을 눌렀을때
        final ImageView starbucksCouponButton=findViewById(R.id.starbucksCouponButton);
        starbucksCouponButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Stamp> call=couponService.getStampCount(userId);
                call.enqueue(new Callback<Stamp>() {   //서버로부터 스탬프 개수 받아서
                    @Override
                    public void onResponse(Call<Stamp> call, Response<Stamp> response) {
                        if(response.isSuccessful()) {
                            try {

                                int starbucksStampCount = response.body().starbucksStampCount;


                            } catch (Exception e) { e.printStackTrace(); }
                        }
                        else{
                            int statusCode=response.code();
                            Log.i("Tag","응답코드:"+statusCode);}

                    }

                    @Override
                    public void onFailure(Call<Stamp> call, Throwable t) {
                    }
                });
            }
        });

        //이디야버튼을 눌렀을때
        ImageView ediyaCouponButton=findViewById(R.id.ediyaCouponButton);
        ediyaCouponButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Stamp> call=couponService.getStampCount(userId);
                call.enqueue(new Callback<Stamp>() {   //서버로부터 스탬프 개수 받아서
                    @Override
                    public void onResponse(Call<Stamp> call, Response<Stamp> response) {
                        if(response.isSuccessful()) {
                            try {

                                int ediyaStampCount = response.body().ediyaStampCount;
                                for (int i = 1; i <= ediyaStampCount; i++) {
                                    int id = getApplicationContext().getResources().getIdentifier("coupon" + i, "id", getApplicationContext().getPackageName());
                                    ImageView Img = (ImageView)findViewById(id);

                                    Img.setImageResource(R.drawable.ediya_icon);    //출력
                                }

                            } catch (Exception e) { e.printStackTrace(); }
                        }
                        else{
                            int statusCode=response.code();
                            Log.i("Tag","응답코드:"+statusCode);}

                    }

                    @Override
                    public void onFailure(Call<Stamp> call, Throwable t) {
                    }
                });
            }
        });

        //할리스버튼을 눌렀을때
        ImageView hollysCouponButton=findViewById(R.id.hollysCouponButton);
        hollysCouponButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Stamp> call=couponService.getStampCount(userId);
                call.enqueue(new Callback<Stamp>() {   //서버로부터 스탬프 개수 받아서
                    @Override
                    public void onResponse(Call<Stamp> call, Response<Stamp> response) {
                        if(response.isSuccessful()) {
                            try {

                                int hollysStampCount = response.body().hollysStampCount;
                                for (int i = 1; i <= hollysStampCount; i++) {
                                    int id = getApplicationContext().getResources().getIdentifier("coupon" + i, "id", getApplicationContext().getPackageName());
                                    ImageView Img = (ImageView)findViewById(id);

                                    Img.setImageResource(R.drawable.hollys_icon);    //출력
                                }

                            } catch (Exception e) { e.printStackTrace(); }
                        }
                        else{
                            int statusCode=response.code();
                            Log.i("Tag","응답코드:"+statusCode);}

                    }

                    @Override
                    public void onFailure(Call<Stamp> call, Throwable t) {

                    }
                });
            }
        });

        //엔젤리너스버튼을 눌렀을때
        ImageView angelinusCouponButton=findViewById(R.id.angelinusCouponButton);
        angelinusCouponButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Stamp> call=couponService.getStampCount(userId);
                call.enqueue(new Callback<Stamp>() {   //서버로부터 스탬프 개수 받아서
                    @Override
                    public void onResponse(Call<Stamp> call, Response<Stamp> response) {
                        if(response.isSuccessful()) {
                            try {

                                int angelinusStampCount = response.body().angelinusStampCount;
                                for (int i = 1; i <= angelinusStampCount; i++) {
                                    int id = getApplicationContext().getResources().getIdentifier("coupon" + i, "id", getApplicationContext().getPackageName());
                                    ImageView Img = (ImageView)findViewById(id);

                                    Img.setImageResource(R.drawable.angelinus_icon);    //출력
                                }

                            } catch (Exception e) { e.printStackTrace(); }
                        }
                        else{
                            int statusCode=response.code();
                            Log.i("Tag","응답코드:"+statusCode);}

                    }

                    @Override
                    public void onFailure(Call<Stamp> call, Throwable t) {

                    }
                });
            }
        });

        //탐앤탐즈버튼을 눌렀을때
        ImageView tomntomsCouponButton=findViewById(R.id.tomntomsCouponButton);
        tomntomsCouponButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Stamp> call=couponService.getStampCount(userId);
                call.enqueue(new Callback<Stamp>() {   //서버로부터 스탬프 개수 받아서
                    @Override
                    public void onResponse(Call<Stamp> call, Response<Stamp> response) {
                        if(response.isSuccessful()) {
                            try {

                                int tomntomsStampCount = response.body().tomntomsStampCount;
                                for (int i = 1; i <= tomntomsStampCount; i++) {
                                    int id = getApplicationContext().getResources().getIdentifier("coupon" + i, "id", getApplicationContext().getPackageName());
                                    ImageView Img = (ImageView)findViewById(id);

                                    Img.setImageResource(R.drawable.tomntoms_icon);    //출력
                                }

                            } catch (Exception e) { e.printStackTrace(); }
                        }
                        else{
                            int statusCode=response.code();
                            Log.i("Tag","응답코드:"+statusCode);}

                    }

                    @Override
                    public void onFailure(Call<Stamp> call, Throwable t) {

                    }
                });
            }
        });






        Button myFullCouponButton=findViewById(R.id.myFullCouponButton);

        myFullCouponButton.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CouponActivity.this,MyFullCouponActivity.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });

    }
}
