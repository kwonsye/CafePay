package com.example.kwons.cafepay;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PaidListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<PayItem> paidList;

    public PaidListAdapter(Context context, ArrayList<PayItem> paidList) {
        this.context = context;
        this.paidList = paidList;
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
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = View.inflate(context, R.layout.paid, null);
        TextView paidLocation = v.findViewById(R.id.paidLocation);
        TextView paidCafeName = v.findViewById(R.id.paidCafeName);
        TextView paidPrice = v.findViewById(R.id.paidPrice);
        TextView paidMenu = v.findViewById(R.id.paidMenu);

        paidLocation.setText(paidList.get(i).cafeLocation);
        paidCafeName.setText(paidList.get(i).cafeName);
        paidPrice.setText(String.valueOf(paidList.get(i).price));
        paidMenu.setText(paidList.get(i).cafeMenu);

        v.setTag(paidList.get(i).id);

        return v;
    }
}
