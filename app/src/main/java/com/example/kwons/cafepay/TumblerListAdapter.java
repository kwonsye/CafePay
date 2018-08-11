package com.example.kwons.cafepay;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TumblerListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<TumblerInfo> infoArrayList;

    public TumblerListAdapter(Context context, ArrayList<TumblerInfo> infoArrayList) {
        this.context = context;
        this.infoArrayList = infoArrayList;
    }

    @Override
    public int getCount() {
        return infoArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return infoArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        View v = View.inflate(context, R.layout.tumbler_info, null);
        TextView tumblerSerialNumberTextView = v.findViewById(R.id.tumblerSerialNumberTextView);
        TextView idTextView = v.findViewById(R.id.idTextView);

        idTextView.setText(String.valueOf(infoArrayList.get(i).getId()));
        tumblerSerialNumberTextView.setText(infoArrayList.get(i).getSerial());
        v.setTag(infoArrayList.get(i).getId());
        return v;
    }

}
