package com.example.fastcredits.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AllCredits {
    @SerializedName("data")
    @Expose
    private ArrayList<Credit> data;

    public ArrayList<Credit> getCredits() {
        return data;
    }
}
