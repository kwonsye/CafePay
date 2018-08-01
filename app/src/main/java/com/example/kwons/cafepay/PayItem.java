package com.example.kwons.cafepay;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sujin.kim on 2018. 7. 31..
 */

public class PayItem {

    //결제 내역 아이템
    @SerializedName("cafeLocation")
    @Expose
    public String cafeLocation;
    @SerializedName("cafeMenu")
    @Expose
    public String cafeMenu;
    @SerializedName("cafeName")
    @Expose
    public String cafeName;
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("price")
    @Expose
    public int price;
    @SerializedName("tumblerSerial")
    @Expose
    public String tumblerSerial;
    @SerializedName("userId")
    @Expose
    public String userId;


}
