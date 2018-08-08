package com.example.kwons.cafepay;

import com.google.gson.annotations.SerializedName;

public class Users {
    @SerializedName("birth")
    String birth;

    @SerializedName("gender")
    String gender;

    @SerializedName("id")
    String id;

    @SerializedName("name")
    String name;

    @SerializedName("point")
    int point;

}
