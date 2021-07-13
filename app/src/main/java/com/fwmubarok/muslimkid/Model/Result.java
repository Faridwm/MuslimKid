package com.fwmubarok.muslimkid.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Result {
    @SerializedName("data")
    private ArrayList<Doa> data;

    public ArrayList<Doa> getData() {
        return data;
    }

    public void setData(ArrayList<Doa> data) {
        this.data = data;
    }
}
