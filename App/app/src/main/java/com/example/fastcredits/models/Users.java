package com.example.fastcredits.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Users {
    @SerializedName("message")
    @Expose
    private ArrayList<User> message;

    public ArrayList<User> getUsers() {
        return message;
    }
}
