package com.example.kwons.cafepay;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
public class PaidListAdapter extends BaseAdapter {

    private Context context;

    private ArrayList<PayItem> paidList;
    private String userId;
    private FragmentManager fragmentManager;

    public PaidListAdapter(Context context, ArrayList<PayItem> paidList, String userId, FragmentManager fm) {
        this.context = context;
        this.paidList = paidList;
        this.userId = userId;
        fragmentManager = fm;
    }

    @Override
    public int getCount() {
        return paidList.size();
    }

    @Override
    public Object getItem(int i) {
        return paidList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        View v = View.inflate(context, R.layout.paid, null);
        TextView paidLocation = v.findViewById(R.id.paidLocation);
        TextView paidCafeName = v.findViewById(R.id.paidCafeName);
        TextView paidPrice = v.findViewById(R.id.paidPrice);
        TextView paidMenu = v.findViewById(R.id.paidMenu);
        ImageView coffeeImageView = v.findViewById(R.id.coffeeImageView);
        Button evaluateButton = v.findViewById(R.id.evaluateButton);

        paidLocation.setText(paidList.get(i).cafeLocation);
        paidCafeName.setText(paidList.get(i).cafeName);
        paidPrice.setText(String.valueOf(paidList.get(i).price));
        paidMenu.setText(paidList.get(i).cafeMenu);
        if(paidMenu.getText().toString().contains("아메리카노")){
            coffeeImageView.setImageResource(R.drawable.americano);
        }else if(paidMenu.getText().toString().contains("카페라떼")){
            coffeeImageView.setImageResource(R.drawable.cafelatte);
        }else if(paidMenu.getText().toString().contains("카페모카")){
            coffeeImageView.setImageResource(R.drawable.mocha);
        }else if(paidMenu.getText().toString().contains("마끼야또")){
            coffeeImageView.setImageResource(R.drawable.macchiato);
        }else{
            coffeeImageView.setImageResource(R.drawable.ask);
        }

        evaluateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RatingPopup dialog = RatingPopup.newInstance(userId, i);
                dialog.show(fragmentManager, "Rating");
            }
        });

        v.setTag(paidList.get(i).id);

        return v;
    }

}
