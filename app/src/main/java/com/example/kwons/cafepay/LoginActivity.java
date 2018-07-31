package com.example.kwons.cafepay;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {



    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        TextView registerButton=findViewById(R.id.registerButton);
        Button loginButton=findViewById(R.id.loginButton);

        registerButton.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent registerIntent=new Intent(LoginActivity.this,RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);

            }
        });

        loginButton.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent loginIntent=new Intent(LoginActivity.this,MainActivity.class);
                LoginActivity.this.startActivity(loginIntent);


                //쿠폰액티비티로 아이디 넘기기-은인언니한테 부탁
                Intent intent = new Intent(getApplicationContext(),CouponActivity.class);
                intent.putExtra("userId", getText(R.id.idText).toString());
                startActivity(intent);


            }
        });



    }
}
