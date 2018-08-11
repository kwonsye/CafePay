package com.example.kwons.cafepay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.kwons.cafepay.Service.UserService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView userNameText = findViewById(R.id.userName);
        TextView couponText = findViewById(R.id.couponButton);
        TextView rechargeText = findViewById(R.id.rechargeButton);
        TextView paidText = findViewById(R.id.paidButton);
        TextView recommendText = findViewById(R.id.evaluateButton);
        Button tumblerRegisterButton = findViewById(R.id.tumblerRegisterButton);

        //로그인액티비티에서 유저아이디 받아오기
        Intent fromLoginIntent = getIntent();
        final String userId = fromLoginIntent.getExtras().getString("userId");


        /**아이디에 해당하는 유저의 이름받아오기*/
        userService = RetrofitClient.getClient().create(UserService.class);
        Call<List<Users>> call = userService.getAllUserList();
        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                if (response.isSuccessful()) {
                    List<Users> users = response.body();
                    for (Users user : users) {
                        if (user.id.toString().equals(userId)) {
                            userNameText.setText(user.name.toString() + "님");
                            return;
                        }
                    }
                } else {
                    int statusCode = response.code();
                    Log.i("Main액티비티", "응답코드:" + statusCode);
                }
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                /**요청실패*/
                Log.i("Main액티비티", "통신에러");
            }
        });

        couponText.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent toCouponIntent = new Intent(MainActivity.this, CouponActivity.class);
                toCouponIntent.putExtra("userId", userId);
                MainActivity.this.startActivity(toCouponIntent);
            }
        });
        rechargeText.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent rechargeIntent = new Intent(MainActivity.this, RechargeActivity.class);
                rechargeIntent.putExtra("userId", userId);
                MainActivity.this.startActivity(rechargeIntent);
            }
        });
        paidText.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent paidIntent = new Intent(MainActivity.this, PaidActivity.class);
                paidIntent.putExtra("userId", userId);
                MainActivity.this.startActivity(paidIntent);

            }
        });
        recommendText.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent recommendIntent = new Intent(MainActivity.this, RecommendActivity.class);
                recommendIntent.putExtra("userId", userId);
                MainActivity.this.startActivity(recommendIntent);

            }
        });

        tumblerRegisterButton.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent tumblerRegisterIntent = new Intent(MainActivity.this, TumblerRegisterActivity.class);
                tumblerRegisterIntent.putExtra("userId", userId);
                MainActivity.this.startActivity(tumblerRegisterIntent);

            }
        });

    }
}
