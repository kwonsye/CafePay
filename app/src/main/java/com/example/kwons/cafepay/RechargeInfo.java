package com.example.kwons.cafepay;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sujin.kim on 2018. 8. 2..
 */

public class RechargeInfo {


    @SerializedName("birth")
    @Expose
    public String birth;

    @SerializedName("gender")
    @Expose
    public String gender;

    @SerializedName("id")
    @Expose
    public String id;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("point")
    @Expose
    public Integer point;

}
