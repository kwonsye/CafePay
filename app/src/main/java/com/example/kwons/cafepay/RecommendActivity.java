package com.example.kwons.cafepay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

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

        //1.사용자의 선호도 리스트뷰

        myPreferListView=(ListView)findViewById(id.myPreferListView);
        preferList=new ArrayList<Prefer>();

        final ScrollView scrollView=(ScrollView)findViewById(id.scrollVIew);
        myPreferListView.setOnTouchListener(new View.OnTouchListener() {
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
        myPreferListView.setAdapter(adapter);

        //2.같은 나이대+같은 성별의 선호도 리스트뷰
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


    }
}
