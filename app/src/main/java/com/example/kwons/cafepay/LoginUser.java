package com.example.kwons.cafepay;

import com.google.gson.annotations.SerializedName;

class LoginUser {

    String id;
    String password;

    public LoginUser(String id, String password) {
        this.id = id;
        this.password = password;
    }
}
