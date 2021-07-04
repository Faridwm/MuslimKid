package com.fwmubarok.muslimkid.MyInterface;

import com.fwmubarok.muslimkid.Model.DailyDoa;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MuslimApiInterface {

    @GET("api/data/json/doaharian")
    Call<DailyDoa> getDailyDoa();
}
