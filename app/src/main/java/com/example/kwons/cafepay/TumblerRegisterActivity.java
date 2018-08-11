package com.example.kwons.cafepay;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kwons.cafepay.Service.TumblerRegisterService;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

//텀블러 인식에 성공하면 성공했다는 Dialog나 Toast를 띄우고 MainActivity로 다시 돌아갑니다.

public class TumblerRegisterActivity extends AppCompatActivity {

    static final String TAG = "PERMISSION";
    private FloatingActionButton qrcodeButton;
    private String userId;
    private String serial;
    private TumblerRegisterService tumblerRegisterService;
    private ArrayList<TumblerInfo> tumblerInfoArrayList;
    private ListView tumblerList;
    private TumblerListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tumbler_register);
        qrcodeButton = findViewById(R.id.qrcode_button);
        qrcodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanQRCode();
            }
        });
        tumblerInfoArrayList = new ArrayList<>();
        tumblerList = findViewById(R.id.tumblerListView);

        Intent fromLoginIntent = getIntent();
        userId = fromLoginIntent.getExtras().getString("userId");

        if (userId == null) {
            Log.d("Intent Error", "userId = null");
        }

        getTumblerInfoList();

    }

    private void getTumblerInfoList() {

        tumblerRegisterService = RetrofitClient.getClient().create(TumblerRegisterService.class);

        Call<List<TumblerInfo>> callTumblerService = tumblerRegisterService.getTumblerById(userId);

        callTumblerService.enqueue(new Callback<List<TumblerInfo>>() {
            @Override
            public void onResponse(Call<List<TumblerInfo>> call, retrofit2.Response<List<TumblerInfo>> response) {
                if (call.isExecuted()) {
                    tumblerInfoArrayList.addAll(response.body());
                    adapter = new TumblerListAdapter(getApplicationContext(), tumblerInfoArrayList);
                    tumblerList.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<TumblerInfo>> call, Throwable t) {
                Log.d("Error ", t.getMessage());
                call.cancel();

            }
        });
    }


    public void scanQRCode() {
        new IntentIntegrator(this).initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IntentIntegrator.REQUEST_CODE) {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                serial = result.getContents();
                Call<JSONObject> callTumblerService = tumblerRegisterService.registerTumblerSerialNumber(new TumblerRegister(serial,userId));

                callTumblerService.enqueue(new Callback<JSONObject>() {
                    @Override
                    public void onResponse(Call<JSONObject> call, retrofit2.Response<JSONObject> response) {
                        if (response.isSuccessful()) {
                            if (response.code() == 200) {
                                Toast.makeText(TumblerRegisterActivity.this, "등록성공", Toast.LENGTH_SHORT).show();
                                getTumblerInfoList();
                            }
                        } else {
                            Log.d("Register", "Error Register");
                        }
                    }

                    @Override
                    public void onFailure(Call<JSONObject> call, Throwable t) {
                        Log.d("Error ", t.getMessage());
                        call.cancel();
                    }
                });
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (Build.VERSION.SDK_INT >= 23) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG, "Permission: " + permissions[0] + "was " + grantResults[0]);
                //resume tasks needing this permission
            }
        }
    }

}
