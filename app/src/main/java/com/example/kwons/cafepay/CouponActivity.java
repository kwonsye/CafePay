package com.example.kwons.cafepay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.style.LeadingMarginSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.kwons.cafepay.Service.CouponService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CouponActivity extends AppCompatActivity {

    CouponService couponService;

    static int starbucksCouponCount;
    static int ediyaCouponCount;
    static int angelinusCouponCount;
    static int hollysCouponCount;
    static int tomntomsCouponCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);

        //Retrofit
        couponService = RetrofitClient.getClient().create(CouponService.class);

        //메인액티비티에서 사용자아이디 받기
        Intent fromMainIntent = getIntent();
        final String userId = fromMainIntent.getExtras().getString("userId");


        //스타벅스버튼을 눌렀을때
        final ImageView starbucksCouponButton = (ImageView) findViewById(R.id.starbucksCouponButton);
        starbucksCouponButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Stamp> call = couponService.getStampCount(userId);
                call.enqueue(new Callback<Stamp>() {   //서버로부터 스탬프 개수 받아서
                    @Override
                    public void onResponse(Call<Stamp> call, Response<Stamp> response) {
                        if (response.isSuccessful()) {
                            try {

                                int starbucksStampCount = (int) response.body().starbucksStampCount;
                                starbucksCouponCount = starbucksStampCount / 10;

                                for (int stampCount = 1; stampCount <= 10; stampCount++) {
                                    int id = getApplicationContext().getResources().getIdentifier("coupon" + stampCount, "id", getApplicationContext().getPackageName());
                                    ImageView Img = (ImageView) findViewById(id);

                                    Img.setImageResource(R.drawable.empty_coupon);    //출력
                                }

                                for (int stampCount = 1; stampCount <= starbucksStampCount % 10; stampCount++) {
                                    int id = getApplicationContext().getResources().getIdentifier("coupon" + stampCount, "id", getApplicationContext().getPackageName());
                                    ImageView Img = (ImageView) findViewById(id);

                                    Img.setImageResource(R.drawable.starbucks_icon);    //출력
                                }


                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            int statusCode = response.code();
                            Log.i("Coupon액티비티", "응답코드:" + statusCode);
                        }

                    }

                    @Override
                    public void onFailure(Call<Stamp> call, Throwable t) {
                        /**요청실패*/
                        Log.i("Coupon액티비티", "통신에러");
                    }
                });
            }
        });

        //초기에는 스타벅스버튼을 누른것으로 초기화
        starbucksCouponButton.performClick();

        //이디야버튼을 눌렀을때
        ImageView ediyaCouponButton = (ImageView) findViewById(R.id.ediyaCouponButton);
        ediyaCouponButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Stamp> call = couponService.getStampCount(userId);
                call.enqueue(new Callback<Stamp>() {   //서버로부터 스탬프 개수 받아서
                    @Override
                    public void onResponse(Call<Stamp> call, Response<Stamp> response) {
                        if (response.isSuccessful()) {
                            try {

                                int ediyaStampCount = (int) response.body().ediyaStampCount;
                                ediyaCouponCount = ediyaStampCount / 10;

                                for (int stampCount = 1; stampCount <= 10; stampCount++) {
                                    int id = getApplicationContext().getResources().getIdentifier("coupon" + stampCount, "id", getApplicationContext().getPackageName());
                                    ImageView Img = (ImageView) findViewById(id);

                                    Img.setImageResource(R.drawable.empty_coupon);    //출력
                                }


                                for (int stampCount = 1; stampCount <= ediyaStampCount % 10; stampCount++) {
                                    int id = getApplicationContext().getResources().getIdentifier("coupon" + stampCount, "id", getApplicationContext().getPackageName());
                                    ImageView Img = (ImageView) findViewById(id);

                                    Img.setImageResource(R.drawable.ediya_icon);    //출력
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            int statusCode = response.code();
                            Log.i("Tag", "응답코드:" + statusCode);
                        }

                    }

                    @Override
                    public void onFailure(Call<Stamp> call, Throwable t) {
                        /**요청실패*/
                        Log.i("Coupon액티비티", "통신에러");
                    }
                });
            }
        });

        //할리스버튼을 눌렀을때
        ImageView hollysCouponButton = (ImageView) findViewById(R.id.hollysCouponButton);
        hollysCouponButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Stamp> call = couponService.getStampCount(userId);
                call.enqueue(new Callback<Stamp>() {   //서버로부터 스탬프 개수 받아서
                    @Override
                    public void onResponse(Call<Stamp> call, Response<Stamp> response) {
                        if (response.isSuccessful()) {
                            try {

                                int hollysStampCount = (int) response.body().hollysStampCount;
                                hollysCouponCount = hollysStampCount / 10;

                                for (int stampCount = 1; stampCount <= 10; stampCount++) {
                                    int id = getApplicationContext().getResources().getIdentifier("coupon" + stampCount, "id", getApplicationContext().getPackageName());
                                    ImageView Img = (ImageView) findViewById(id);

                                    Img.setImageResource(R.drawable.empty_coupon);    //출력
                                }


                                for (int stampCount = 1; stampCount <= hollysStampCount % 10; stampCount++) {
                                    int id = getApplicationContext().getResources().getIdentifier("coupon" + stampCount, "id", getApplicationContext().getPackageName());
                                    ImageView Img = (ImageView) findViewById(id);

                                    Img.setImageResource(R.drawable.hollys_icon);    //출력
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            int statusCode = response.code();
                            Log.i("Tag", "응답코드:" + statusCode);
                        }

                    }

                    @Override
                    public void onFailure(Call<Stamp> call, Throwable t) {
                        /**요청실패*/
                        Log.i("Coupon액티비티", "통신에러");
                    }
                });
            }
        });

        //엔젤리너스버튼을 눌렀을때
        ImageView angelinusCouponButton = (ImageView) findViewById(R.id.angelinusCouponButton);
        angelinusCouponButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Stamp> call = couponService.getStampCount(userId);
                call.enqueue(new Callback<Stamp>() {   //서버로부터 스탬프 개수 받아서
                    @Override
                    public void onResponse(Call<Stamp> call, Response<Stamp> response) {
                        if (response.isSuccessful()) {
                            try {

                                int angelinusStampCount = (int) response.body().angelinusStampCount;
                                angelinusCouponCount = angelinusStampCount / 10;

                                for (int stampCount = 1; stampCount <= 10; stampCount++) {
                                    int id = getApplicationContext().getResources().getIdentifier("coupon" + stampCount, "id", getApplicationContext().getPackageName());
                                    ImageView Img = (ImageView) findViewById(id);

                                    Img.setImageResource(R.drawable.empty_coupon);    //출력
                                }


                                for (int stampCount = 1; stampCount <= angelinusStampCount % 10; stampCount++) {
                                    int id = getApplicationContext().getResources().getIdentifier("coupon" + stampCount, "id", getApplicationContext().getPackageName());
                                    ImageView Img = (ImageView) findViewById(id);

                                    Img.setImageResource(R.drawable.angelinus_icon);    //출력
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            int statusCode = response.code();
                            Log.i("Tag", "응답코드:" + statusCode);
                        }

                    }

                    @Override
                    public void onFailure(Call<Stamp> call, Throwable t) {
                        /**요청실패*/
                        Log.i("Coupon액티비티", "통신에러");

                    }
                });
            }
        });

        //탐앤탐즈버튼을 눌렀을때
        ImageView tomntomsCouponButton = (ImageView) findViewById(R.id.tomntomsCouponButton);
        tomntomsCouponButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Stamp> call = couponService.getStampCount(userId);
                call.enqueue(new Callback<Stamp>() {   //서버로부터 스탬프 개수 받아서
                    @Override
                    public void onResponse(Call<Stamp> call, Response<Stamp> response) {
                        if (response.isSuccessful()) {
                            try {

                                int tomntomsStampCount = (int) response.body().tomntomsStampCount;
                                tomntomsCouponCount = tomntomsStampCount / 10;

                                for (int stampCount = 1; stampCount <= 10; stampCount++) {
                                    int id = getApplicationContext().getResources().getIdentifier("coupon" + stampCount, "id", getApplicationContext().getPackageName());
                                    ImageView Img = (ImageView) findViewById(id);

                                    Img.setImageResource(R.drawable.empty_coupon);    //출력
                                }


                                for (int stampCount = 1; stampCount <= tomntomsStampCount % 10; stampCount++) {
                                    int id = getApplicationContext().getResources().getIdentifier("coupon" + stampCount, "id", getApplicationContext().getPackageName());
                                    ImageView Img = (ImageView) findViewById(id);

                                    Img.setImageResource(R.drawable.tomntoms_icon);    //출력
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            int statusCode = response.code();
                            Log.i("Tag", "응답코드:" + statusCode);
                        }

                    }

                    @Override
                    public void onFailure(Call<Stamp> call, Throwable t) {
                        /**요청실패*/
                        Log.i("Coupon액티비티", "통신에러");

                    }
                });
            }
        });


        LinearLayout myFullCouponButton = (LinearLayout) findViewById(R.id.myFullCouponButton);
        myFullCouponButton.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CouponActivity.this, MyFullCouponActivity.class);
                //intent.putExtra("userId", userId);

                intent.putExtra("myStarbucksCoupon", starbucksCouponCount);
                intent.putExtra("myEdiyaCoupon", ediyaCouponCount);
                intent.putExtra("myHollysCoupon", hollysCouponCount);
                intent.putExtra("myAngelinusCoupon", angelinusCouponCount);
                intent.putExtra("myTomnTomsCoupon", tomntomsCouponCount);

                startActivity(intent);
            }
        });

    }
}
