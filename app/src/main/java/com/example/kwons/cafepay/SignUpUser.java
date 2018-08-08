package com.example.kwons.cafepay;

public class SignUpUser {

    String birth;
    String gender;
    String id;
    String name;
    String password;

    public SignUpUser(String birth, String gender, String id, String name, String password) {
        this.birth = birth;

        if(gender.equals("여성"))
            this.gender="F";
        else if(gender.equals("남성"))
            this.gender="M";

        this.id = id;
        this.name = name;
        this.password = password;
    }


}
