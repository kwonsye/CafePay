package com.example.kwons.cafepay;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_welcome);

        /**WIFI나 모바일네트워크 연결 확인*/
      if(isNetWork()==true)//연결이 됐으면
        {
            Log.i("Welcome 액티비티" , "연결됨");
            setContentView(R.layout.activity_welcome);
        }
        if(isNetWork()==false){
            Log.i("Welcome 액티비티" , "연결안됨");
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("오프라인 상태입니다. 네트워크 연결을 확인해주세요.")
                    .setNegativeButton("확인",null)
                    .create()
                    .show();
            return;

        }

        Button loginButton=findViewById(R.id.WelcomeloginButton);
        Button rigisterButton=findViewById(R.id.WelcomeregisterButton);

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

    /**WIFI나 모바일네트워크 연결 확인*/
    private boolean isNetWork(){
        ConnectivityManager manager = (ConnectivityManager) getSystemService (getApplicationContext().CONNECTIVITY_SERVICE);
        boolean isMobileAvailable = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isAvailable();
        boolean isMobileConnect = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
        boolean isWifiAvailable = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isAvailable();
        boolean isWifiConnect = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();

        if ((isWifiAvailable && isWifiConnect) || (isMobileAvailable && isMobileConnect)){
            return true;
        }else{
            return false;
        }
    }





}
