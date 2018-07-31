package com.example.kwons.cafepay;

import java.util.ArrayList;

/**
 * Created by sujin.kim on 2018. 7. 31..
 */

public class PayItemList {

    private int paymentCount;
    private ArrayList<PayItem> paymentList;


    public ArrayList<PayItem> getPayItemList() {
        return paymentList;
    }

    public int getPaymentCount() {
        return paymentCount;
    }

    public PayItemList setPaymentCount(int paymentCount) {
        this.paymentCount = paymentCount;
        return this;
    }

    public PayItemList setPaymentList(ArrayList<PayItem> paymentList) {
        this.paymentList = paymentList;
        return this;
    }

}
