package com.example.kwons.cafepay;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class PaidListAdapter extends BaseAdapter{

    private Context context;
    private List<Paid> paidList;

    public PaidListAdapter(Context context, List<Paid> paidList) {
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

        View v=View.inflate(context,R.layout.paid,null);
        TextView paidDate=(TextView)v.findViewById(R.id.paidDate);
        TextView paidCafeName=(TextView)v.findViewById(R.id.paidCafeName);
        TextView paidPrice=(TextView)v.findViewById(R.id.paidPrice);

        paidDate.setText(paidList.get(i).paidDate);
        paidCafeName.setText(paidList.get(i).paidCafeName);
        paidPrice.setText(paidList.get(i).paidPrice);

        v.setTag(paidList.get(i).getPaidCafeName());

        return v;
    }
}
