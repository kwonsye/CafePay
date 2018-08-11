package com.example.kwons.cafepay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.kwons.cafepay.Service.CouponService;
import com.example.kwons.cafepay.Service.PayItemListService;
import com.example.kwons.cafepay.Service.UserService;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sujin.kim on 2018. 8. 12..
 */

public class EnviromentActivity extends AppCompatActivity {

    PayItemListService payItemListService;
    int paymentCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environment);

        final TextView saveCup=findViewById(R.id.saveCup);
        final TextView saveCoin=findViewById(R.id.saveCoin);
        final TextView saveTree=findViewById(R.id.saveTree);

        //나중에 유저아이디 넘겨주기
        Intent intent=getIntent();
        final String userId=intent.getExtras().getString("userId");

        //결제가 한번씩 될때마다 수치 올려주기
        payItemListService=RetrofitClient.getClient().create(PayItemListService.class);
        Call<PayItemList> call=payItemListService.getPayItemList(userId);
        call.enqueue(new Callback<PayItemList>() {
            @Override
            public void onResponse(Call<PayItemList> call, Response<PayItemList> response) {
                if(response.isSuccessful()) {
                    PayItemList payItemList = response.body();
                    paymentCount=payItemList.getPaymentCount();

                    saveCup.setText(paymentCount*3+"개");
                    saveCoin.setText(paymentCount*21+"원");
                    saveTree.setText((double)paymentCount*0.003+"그루");
                }
                else{
                    int statusCode=response.code();
                    Log.i("Environment액티비티","응답코드:"+statusCode);}
            }

            @Override
            public void onFailure(Call<PayItemList> call, Throwable t) {
                /**요청실패*/
                Log.i("Environment액티비티", "통신에러");
            }
        });

    }
}
