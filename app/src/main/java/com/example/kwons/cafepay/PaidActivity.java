package com.example.kwons.cafepay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.kwons.cafepay.Service.PayItemListService;
import com.example.kwons.cafepay.Service.RechargeService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PaidActivity extends AppCompatActivity {

    private ListView listView;
    private PaidListAdapter adapter;
    private ArrayList<PayItem> payItemList;
    private FragmentManager fm;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paid);

        listView = findViewById(R.id.paidListView);
        payItemList = new ArrayList<>();
        Intent fromLoginIntent = getIntent();
        userId = fromLoginIntent.getExtras().getString("userId");
        fm = getSupportFragmentManager();
        if(userId == null){
            Log.d("Intent Error" , "userId = null");
        }

        PayItemListService payItemListService = RetrofitClient.getClient().create(PayItemListService.class);
        Call<PayItemList> callPayItemList = payItemListService.getPayItemList(userId);

        callPayItemList.enqueue(new Callback<PayItemList>() {
            @Override
            public void onResponse(Call<PayItemList> call, retrofit2.Response<PayItemList> response) {
                PayItemList res = response.body();
                payItemList.addAll(res.getPayItemList());
                adapter = new PaidListAdapter(getApplicationContext(), payItemList, userId, fm);
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<PayItemList> call, Throwable t) {
                Log.d("Error ", t.getMessage());
                call.cancel();

            }
        });


    }
}
