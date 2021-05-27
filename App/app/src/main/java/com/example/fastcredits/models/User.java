package com.example.fastcredits.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class User {
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

    @SerializedName("rol")
    @Expose
    private String rol;

    public String getId() {
        return id;
    }

    public String getFullName() {
        return names + " " + lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getCellphone() {
        return cellphone;
    }

    public String getRole() { return rol; }

    public Boolean getState() { return state; }

    public Date getDate() { return date; }

    public User(String email, String document, String names, String lastname, String gender, String country, String address, String cellphone, Date date, Boolean state, String rol) {
        this.email = email;
        this.document = document;
        this.names = names;
        this.lastname = lastname;
        this.gender = gender;
        this.country = country;
        this.address = address;
        this.cellphone = cellphone;
        this.date = date;
        this.state = state;
        this.rol = rol;
    }
}