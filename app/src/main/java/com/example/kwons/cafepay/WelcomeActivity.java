package com.example.kwons.cafepay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Button loginButton=(Button)findViewById(R.id.WelcomeloginButton);
        Button rigisterButton=(Button)findViewById(R.id.WelcomeregisterButton);

        loginButton.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent loginIntent=new Intent(WelcomeActivity.this,LoginActivity.class);
                WelcomeActivity.this.startActivity(loginIntent);

            }
        });
        rigisterButton.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent registerIntent=new Intent(WelcomeActivity.this,RegisterActivity.class);
                WelcomeActivity.this.startActivity(registerIntent);

            }
        });
    }





}
