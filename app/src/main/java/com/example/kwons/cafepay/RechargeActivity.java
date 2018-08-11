package com.example.kwons.cafepay;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kwons.cafepay.Service.RechargeService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RechargeActivity extends AppCompatActivity {

    private Button rechargeButton;
    private TextView leftPointTextView;
    private RechargeService rechargeService;
    private String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        Intent fromLoginIntent = getIntent();
        userId = fromLoginIntent.getExtras().getString("userId");
        if(userId == null){
            Log.d("Intent error", "userID = null");
        }


        rechargeButton = findViewById(R.id.rechargeButton);
        leftPointTextView = findViewById(R.id.leftPointTextView);
        rechargeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDialog();
            }
        });


    }


    private void createDialog() {
        final AlertDialog.Builder tumblrDialog = new AlertDialog.Builder(RechargeActivity.this, R.style.MyAlertDialogStyle);

        tumblrDialog.setTitle("포인트 충전");       // 제목 설정
        tumblrDialog.setMessage("충전할 금액을 입력해주세요");   // 내용 설정

        final EditText et = new EditText(RechargeActivity.this);
        tumblrDialog.setView(et);

        tumblrDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final int point = Integer.valueOf(et.getText().toString());
                rechargeService = RetrofitClient.getClient().create(RechargeService.class);
                Call<RechargeInfo> callRechargeInfo = rechargeService.recharge(userId, point);

                callRechargeInfo.enqueue(new Callback<RechargeInfo>() {
                    @Override
                    public void onResponse(Call<RechargeInfo> call, retrofit2.Response<RechargeInfo> response) {
                        if (call.isExecuted()) {
                            Toast.makeText(RechargeActivity.this, "충전성공", Toast.LENGTH_SHORT).show();
                            RechargeInfo rechargeInfo = response.body();
                            leftPointTextView.setText(String.valueOf(rechargeInfo.point));
                        }
                    }

                    @Override
                    public void onFailure(Call<RechargeInfo> call, Throwable t) {
                        Log.d("Error ", t.getMessage());
                        call.cancel();
                    }
                });
                dialog.dismiss();     //닫기
                // Event
            }
        });
        tumblrDialog.show();
    }
}
