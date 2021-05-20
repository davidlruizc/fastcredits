package com.example.fastcredits.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LenderResponse {
    @SerializedName("statusCode")
    @Expose
    private int statusCode;

    @SerializedName("message")
    @Expose
    private String message;

    @Override
    public String toString() {
        return message;
    }
}
