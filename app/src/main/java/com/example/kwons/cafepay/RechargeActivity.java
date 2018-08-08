package com.example.kwons.cafepay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kwons.cafepay.Service.RechargeService;

import retrofit2.Call;
import retrofit2.Callback;

public class RechargeActivity extends AppCompatActivity {

    private int point = 600;
    private Button rechargeButton;
    private TextView leftPointTextView;
    private RechargeService rechargeService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        Intent fromLoginIntent = getIntent();
        final String userId = fromLoginIntent.getExtras().getString("userId");

        rechargeButton = findViewById(R.id.rechargeButton);
        leftPointTextView = findViewById(R.id.leftPointTextView);
        rechargeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rechargeService = RetrofitClient.getClient().create(RechargeService.class);
                Call<RechargeInfo> callRechargeInfo = rechargeService.recharge(userId, point);

                callRechargeInfo.enqueue(new Callback<RechargeInfo>() {
                    @Override
                    public void onResponse(Call<RechargeInfo> call, retrofit2.Response<RechargeInfo> response) {
                        if (call.isExecuted()) {
                            Toast.makeText(RechargeActivity.this, "충전성공", Toast.LENGTH_SHORT).show();
                            RechargeInfo rechargeInfo = response.body();
                            leftPointTextView.setText(rechargeInfo.point);
                        }
                    }

                    @Override
                    public void onFailure(Call<RechargeInfo> call, Throwable t) {
                        Log.d("Error ", t.getMessage());
                        call.cancel();
                    }
                });
            }
        });

    }
}
