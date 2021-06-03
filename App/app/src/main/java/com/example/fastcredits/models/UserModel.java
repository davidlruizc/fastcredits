package com.example.fastcredits.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class UserModel {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("cellphone")
    @Expose
    private String cellphone;

    @SerializedName("country")
    @Expose
    private String country;

    @SerializedName("document")
    @Expose
    private String document;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("gender")
    @Expose
    private String gender;

    @SerializedName("lastname")
    @Expose
    private String lastname;

    @SerializedName("names")
    @Expose
    private String names;

    @SerializedName("state")
    @Expose
    private Boolean state;

    @SerializedName("date")
    @Expose
    private Date date;

    @Override
    public String toString() {
        return names;
    }
}
