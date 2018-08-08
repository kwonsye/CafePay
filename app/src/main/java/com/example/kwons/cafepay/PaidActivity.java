package com.example.kwons.cafepay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.kwons.cafepay.Service.PayItemListService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PaidActivity extends AppCompatActivity {

    private ListView listView;
    private PaidListAdapter adapter;
    private ArrayList<PayItem> payItemList;
    String userId = "choiashyusasnjssin";

    /*
        Method Parameters : 메서드 내에서 전달할 수 있는 다양한 매개 변수 옵션이 있습니다.

        @Body - request body로 Java 객체를 전달합니다.
        @Url - 동적인 URL이 필요할때 사용합니다.
        @Query - 쿼리를 추가할 수 있으며, 쿼리를 URL 인코딩하려면 다음과 같이 작성합니다.
        @Query(value = “auth_token”,encoded = true) String auth_token
        @Field - POST에서만 동작하며 form-urlencoded로 데이터를 전송합니다. 이 메소드에는 @FormUrlEncoded 어노테이션이 추가되어야 합니다.

     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paid);

        listView = findViewById(R.id.paidListView);
        payItemList = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PayItemListService.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PayItemListService openApiService = retrofit.create(PayItemListService.class);
        Call<PayItemList> callPayItemList = openApiService.getPayItemList(userId);

        callPayItemList.enqueue(new Callback<PayItemList>() {
            @Override
            public void onResponse(Call<PayItemList> call, retrofit2.Response<PayItemList> response) {
                PayItemList res = response.body();
                payItemList.addAll(res.getPayItemList());
                adapter = new PaidListAdapter(getApplicationContext(), payItemList);
                listView.setAdapter(adapter);
                listView.requestLayout();
            }

            @Override
            public void onFailure(Call<PayItemList> call, Throwable t) {
                Log.d("Error ", t.getMessage());
                call.cancel();

            }
        });


    }
}
