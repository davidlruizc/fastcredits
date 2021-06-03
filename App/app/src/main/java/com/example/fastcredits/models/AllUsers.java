package com.example.fastcredits.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AllUsers {
    @SerializedName("data")
    @Expose
    private ArrayList<UserModel> data;

    public ArrayList<UserModel> getUsers() {
        return data;
    }
}
