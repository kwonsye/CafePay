package com.example.kwons.cafepay.Service;

import com.example.kwons.cafepay.LoginUser;
import com.example.kwons.cafepay.SignUpUser;
import com.example.kwons.cafepay.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {

    //1.유저목록조회-아이디중복조회에서 이용
    @GET("users")
    Call<List<Users>> getAllUserList();

    //2.유저회원가입
    @POST("users")
   // @Headers({"Content-Type: application/json;charset=utf-8"})
    Call<Void> postSignUpUser(@Body SignUpUser signUpUser);

    //3.로그인api
    @POST("users/login")
    Call<Void> postLoginUser(@Body LoginUser loginUser);


}
