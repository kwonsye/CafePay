package com.example.kwons.cafepay;

import com.google.gson.annotations.SerializedName;

class SignUpUser {

    String birth;
    String gender;
    String id;
    String name;
    String password;

    public SignUpUser(String birth, String gender, String id, String name, String password) {
        this.birth = birth;
        this.gender = gender;
        this.id = id;
        this.name = name;
        this.password = password;
    }
}
