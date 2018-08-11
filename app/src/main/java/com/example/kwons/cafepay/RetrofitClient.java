package com.example.kwons.cafepay;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;

    //public static final String API_URL="http://phil6641.cafe24.com:9287/";
    public static final String API_URL="http://lottehotel.koreacentral.cloudapp.azure.com:9287/";

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
