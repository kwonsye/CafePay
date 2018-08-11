package com.example.kwons.cafepay;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecommendListAdapter extends BaseAdapter {

    private Context context;
    private List<Recommend> recommendList;

    public RecommendListAdapter(Context context, List<Recommend> recommendList) {
        this.context = context;
        this.recommendList = recommendList;
    }

    @Override
    public int getCount() {
        return recommendList.size();
    }

    @Override
    public Object getItem(int i) {
        return recommendList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("ResourceType")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        View v = View.inflate(context, R.layout.recommend, null);
        ImageView coffeeImageView = v.findViewById(R.id.coffeeImageView);
        TextView cafeMenu = v.findViewById(R.id.cafeMenu);
        TextView cafeName = v.findViewById(R.id.cafeName);
        TextView cafeLocation = v.findViewById(R.id.cafeLocation);
        TextView cafePrice = v.findViewById(R.id.cafePrice);

        if(cafeMenu.getText().toString().contains("아메리카노"))
            coffeeImageView.setImageResource(R.drawable.americano);
        else if(cafeMenu.getText().toString().contains("카페라떼"))
            coffeeImageView.setImageResource(R.drawable.cafelatte);
        else if(cafeMenu.getText().toString().contains("카페모카"))
            coffeeImageView.setImageResource(R.drawable.mocha);
        else if(cafeMenu.getText().toString().contains("마끼야또"))
            coffeeImageView.setImageResource(R.drawable.macchiato);
        else{
            coffeeImageView.setImageResource(R.drawable.ask);
        }

        cafeMenu.setText(recommendList.get(i).getCafeMenu());
        cafeLocation.setText(recommendList.get(i).getCafeLocation());
        cafeName.setText(recommendList.get(i).getCafeName());
        cafePrice.setText(String.valueOf(recommendList.get(i).getPrice()));
        v.setTag(recommendList.get(i).getId());
        return v;
    }
}
