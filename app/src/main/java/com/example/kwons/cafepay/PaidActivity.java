package com.example.kwons.cafepay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class PaidActivity extends AppCompatActivity {

    private ListView listView;
    private PaidListAdapter adapter;
    private List<Paid> paidList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paid);

        listView=(ListView)findViewById(R.id.paidListView);
        paidList=new ArrayList<Paid>();

        //디비연동전 예시데이터
        paidList.add(new Paid("7/18","스타벅스","5000원"));
        paidList.add(new Paid("7/19","이디야","3000원"));
        paidList.add(new Paid("7/20","엔젤리너스","4000원"));
        paidList.add(new Paid("7/20","할리스","5000원"));
        paidList.add(new Paid("7/22","할리스","3000원"));
        paidList.add(new Paid("7/24","스타벅스","7000원"));
        paidList.add(new Paid("7/24","스타벅스","7000원"));
        paidList.add(new Paid("7/24","스타벅스","7000원"));
        paidList.add(new Paid("7/24","스타벅스","7000원"));
        paidList.add(new Paid("7/24","스타벅스","7000원"));



        adapter=new PaidListAdapter(getApplicationContext(),paidList);
        listView.setAdapter(adapter);




    }
}
