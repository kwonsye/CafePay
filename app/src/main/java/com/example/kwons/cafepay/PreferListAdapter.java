package com.example.kwons.cafepay;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PreferListAdapter extends BaseAdapter{

    private Context context;
    private List<Prefer> preferList;

    public PreferListAdapter(Context context, List<Prefer> preferList) {
        this.context = context;
        this.preferList = preferList;
    }

    @Override
    public int getCount() {
        return preferList.size();
    }

    @Override
    public Object getItem(int i) {
        return preferList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("ResourceType")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        View v=View.inflate(context,R.layout.prefer,null);
        ImageView preferImg=(ImageView)v.findViewById(R.id.preferImg);
        TextView preferCafeName=(TextView)v.findViewById(R.id. preferCafeName);
        TextView preferMenu=(TextView)v.findViewById(R.id.preferMenu);

        preferImg.setImageResource(preferList.get(i).preferImg);
        preferCafeName.setText(preferList.get(i).preferCafeName);
        preferMenu.setText(preferList.get(i).preferMenu);

        v.setTag(preferList.get(i).getPreferMenu());

        return v;
    }
}
