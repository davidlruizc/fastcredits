package com.example.fastcredits.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class EnableDisableUser {
    @SerializedName("message")
    @Expose
    private String message;

    public String getResponse() {
        return message;
    }
}
