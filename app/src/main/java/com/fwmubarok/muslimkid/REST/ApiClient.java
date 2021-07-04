package com.fwmubarok.muslimkid.REST;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BASE_URL = "https://islamic-api-indonesia.herokuapp.com";
    public static Retrofit retrofit;

    public static Retrofit getClient() {
        retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        return retrofit;
    }
}
