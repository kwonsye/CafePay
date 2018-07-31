package com.example.kwons.cafepay;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

public class FullCouponAdapter extends BaseAdapter{

    private Context context;
    private List<FullCoupon> fullCouponList;

    public FullCouponAdapter(Context context, List<FullCoupon> fullCouponList) {
        this.context = context;
        this.fullCouponList = fullCouponList;
    }

    @Override
    public int getCount() {
        return fullCouponList.size();
    }

    @Override
    public Object getItem(int i) {
        return fullCouponList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.fullcoupon, null);

        for (int j = 1; j <= 10; j++) { ;
            int id = context.getResources().getIdentifier("coupon"+j, "id", context.getPackageName());
            ImageView Img = (ImageView) v.findViewById(id);

            if(fullCouponList.get(i).getCafeNameOfCoupon().equals("스타벅스"))
                Img.setImageResource(R.drawable.starbucks_icon);

            else if(fullCouponList.get(i).getCafeNameOfCoupon().equals("이디야"))
                Img.setImageResource(R.drawable.ediya_icon);

            else if(fullCouponList.get(i).getCafeNameOfCoupon().equals("할리스"))
                Img.setImageResource(R.drawable.hollys_icon);

            else if(fullCouponList.get(i).getCafeNameOfCoupon().equals("탐앤탐스"))
                Img.setImageResource(R.drawable.tomntoms_icon);

            else if(fullCouponList.get(i).getCafeNameOfCoupon().equals("엔젤리너스"))
                Img.setImageResource(R.drawable.angelinus_icon);



        }

        v.setTag(fullCouponList.get(i).getCafeNameOfCoupon());

        return v;
    }

}
