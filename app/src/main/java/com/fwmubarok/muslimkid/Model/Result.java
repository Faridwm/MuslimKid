package com.fwmubarok.muslimkid.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {
    @SerializedName("data")
    private List<Doa> data;

    public List<Doa> getData() {
        return data;
    }

    public void setData(List<Doa> data) {
        this.data = data;
    }
}
