package com.example.kwons.cafepay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.kwons.cafepay.Service.RecommendService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import static com.example.kwons.cafepay.R.id;
import static com.example.kwons.cafepay.R.layout;

public class RecommendActivity extends AppCompatActivity {

    private ListView recommendListView;
    private RecommendListAdapter adapter;
    private List<Recommend> recommendArrayList;
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_recommend);

        Intent fromLoginIntent = getIntent();
        userId = fromLoginIntent.getExtras().getString("userId");

        if (userId == null) {
            Log.d("Intent Error", "userId = null");
        }

        recommendListView = findViewById(id.recommendListView);
        recommendArrayList = new ArrayList<>();
        getRecommendList();
    }

    private void getRecommendList() {

        RecommendService recommendService = RetrofitClient.getClient().create(RecommendService.class);

        Call<List<Recommend>> callTumblerService = recommendService.recommend(userId);

        callTumblerService.enqueue(new Callback<List<Recommend>>() {
            @Override
            public void onResponse(Call<List<Recommend>> call, retrofit2.Response<List<Recommend>> response) {
                if (call.isExecuted()) {
                    List<Recommend> responseList = response.body();
                    if (responseList != null)
                        recommendArrayList.addAll(responseList);
                    adapter = new RecommendListAdapter(getApplicationContext(), recommendArrayList);
                    recommendListView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Recommend>> call, Throwable t) {
                Log.d("Error ", t.getMessage());
                call.cancel();
            }
        });
    }
}