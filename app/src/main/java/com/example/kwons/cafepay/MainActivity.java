package com.example.kwons.cafepay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView couponText = findViewById(R.id.couponButton);
        TextView rechargeText = findViewById(R.id.rechargeButton);
        TextView paidText = findViewById(R.id.paidButton);
        TextView recommendText = findViewById(R.id.recommendButton);
        Button tumblerRegisterButton = findViewById(R.id.tumblerRegisterButton);

        couponText.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent couponIntent = new Intent(MainActivity.this, CouponActivity.class);
                MainActivity.this.startActivity(couponIntent);

            }
        });
        rechargeText.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent rechargeIntent = new Intent(MainActivity.this, RechargeActivity.class);
                MainActivity.this.startActivity(rechargeIntent);
            }
        });
        paidText.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent paidIntent = new Intent(MainActivity.this, PaidActivity.class);
                MainActivity.this.startActivity(paidIntent);

            }
        });
        recommendText.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent recommendIntent = new Intent(MainActivity.this, RecommendActivity.class);
                MainActivity.this.startActivity(recommendIntent);

            }
        });

        tumblerRegisterButton.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent tumblerRegisterIntent = new Intent(MainActivity.this, TumblerRegisterActivity.class);
                MainActivity.this.startActivity(tumblerRegisterIntent);

            }
        });

    }
}
