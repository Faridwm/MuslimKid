package com.fwmubarok.muslimkid.Model;

import com.google.gson.annotations.SerializedName;

public class DailyDoa {
    @SerializedName("result")
    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
