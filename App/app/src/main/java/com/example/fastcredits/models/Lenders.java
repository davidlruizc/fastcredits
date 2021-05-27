package com.example.fastcredits.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Lenders {
    @SerializedName("message")
    @Expose
    private ArrayList<Lender> message;

    public ArrayList<Lender> getLenders() {
        return message;
    }
}
