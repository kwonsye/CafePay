package com.example.kwons.cafepay;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sujin.kim on 2018. 7. 31..
 */

public class PayItem {

    //결제 내역 아이템
    @SerializedName("cafeLocation")
    public String cafeLocation;
    @SerializedName("cafeMenu")
    public String cafeMenu;
    @SerializedName("cafeName")
    public String cafeName;
    @SerializedName("id")
    public int id;
    @SerializedName("price")
    public int price;
    @SerializedName("tumblerSerial")
    public String tumblerSerial;
    @SerializedName("userId")
    public String userId;


}
