package com.example.kwons.cafepay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.kwons.cafepay.Service.UserService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.kwons.cafepay.R.drawable;
import static com.example.kwons.cafepay.R.id;
import static com.example.kwons.cafepay.R.layout;

public class RecommendActivity extends AppCompatActivity {

    private ListView myPreferListView;
    private ListView myAgeGroupPreferListView;
    private PreferListAdapter adapter;
    private List<Prefer> preferList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_recommend);

        //유저아이디받아오기
        Intent fromMainIntent=getIntent();
        final String userId=fromMainIntent.getExtras().getString("userId");

        /**아이디에 해당하는 유저의 이름받아오기*/
        final TextView myPreferTextId=(TextView) findViewById(id.myPreferText);
        UserService userService = RetrofitClient.getClient().create(UserService.class);
        Call<List<Users>> call=userService.getAllUserList();
        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                if(response.isSuccessful()) {
                    List<Users> users = response.body();
                    for (Users user : users) {
                        if (user.id.toString().equals(userId)) {
                            myPreferTextId.setText(user.name.toString());
                            return;
                        }
                    }
                }
                else{
                    int statusCode=response.code();
                    Log.i("Recommend액티비티","응답코드:"+statusCode);}
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                /**요청실패*/
                Log.i("Recommend액티비티", "통신에러");
            }
        });

        //1.사용자의 선호도 리스트뷰

        myPreferListView = (ListView) findViewById(id.myPreferListView);
        preferList = new ArrayList<Prefer>();

       /* final LiView scrollView = (ScrollView) findViewById(id.scrollVIew);
        myPreferListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                scrollView.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });*/


        //디비연동전 예시데이터

        preferList.add(new Prefer(drawable.prefer_example_img, "스타벅스", "아이스 아메리카노"));
        preferList.add(new Prefer(drawable.prefer_example_img, "스타벅스", "아이스 아메리카노"));
        preferList.add(new Prefer(drawable.prefer_example_img, "스타벅스", "아이스 아메리카노"));
        preferList.add(new Prefer(drawable.prefer_example_img, "스타벅스", "아이스 아메리카노"));
        preferList.add(new Prefer(drawable.prefer_example_img, "스타벅스", "아이스 아메리카노"));

        adapter = new PreferListAdapter(getApplicationContext(), preferList);
        myPreferListView.setAdapter(adapter);

        /*2.같은 나이대+같은 성별의 선호도 리스트뷰
        myAgeGroupPreferListView=(ListView)findViewById(id.myAgeGroupPreferListView);
       preferList=new ArrayList<Prefer>();

        myAgeGroupPreferListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                scrollView.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        //디비연동전 예시데이터

        preferList.add(new Prefer(drawable.prefer_example_img,"스타벅스","아이스 아메리카노"));
        preferList.add(new Prefer(drawable.prefer_example_img,"스타벅스","아이스 아메리카노"));
        preferList.add(new Prefer(drawable.prefer_example_img,"스타벅스","아이스 아메리카노"));
        preferList.add(new Prefer(drawable.prefer_example_img,"스타벅스","아이스 아메리카노"));
        preferList.add(new Prefer(drawable.prefer_example_img,"스타벅스","아이스 아메리카노"));

        adapter=new PreferListAdapter(getApplicationContext(),preferList);
        myAgeGroupPreferListView.setAdapter(adapter);


    }*/
    }
}