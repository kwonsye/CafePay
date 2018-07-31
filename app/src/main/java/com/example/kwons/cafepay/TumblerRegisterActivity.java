package com.example.kwons.cafepay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//텀블러 인식에 성공하면 성공했다는 Dialog나 Toast를 띄우고 MainActivity로 다시 돌아갑니다.

public class TumblerRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tumbler_register);
    }
}
