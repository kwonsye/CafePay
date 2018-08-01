package com.example.kwons.cafepay;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stamp {


    //스탬프개수
    @SerializedName("angelinusCouponCount")
    @Expose public int angelinusStampCount;

    @SerializedName("ediyaCouponCount")
    @Expose public int ediyaStampCount;

    @SerializedName("hollysCouponCount")
    @Expose public int hollysStampCount;

    @SerializedName("starbucksCouponCount")
    @Expose public int starbucksStampCount;

    @SerializedName("tomntomsCouponCount")
    @Expose public int tomntomsStampCount;



}
