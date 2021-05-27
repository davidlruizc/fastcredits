package com.example.fastcredits.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Lender {
    @SerializedName("_id")
    @Expose
    private String Id;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("document")
    @Expose
    private String document;

    @SerializedName("names")
    @Expose
    private String names;

    @SerializedName("lastname")
    @Expose
    private String lastname;

    @SerializedName("gender")
    @Expose
    private String gender;

    @SerializedName("country")
    @Expose
    private String country;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("cellphone")
    @Expose
    private String cellphone;

    @SerializedName("date")
    @Expose
    private Date date;

    @SerializedName("state")
    @Expose
    private Boolean state;

    @SerializedName("active")
    @Expose
    private Boolean active;

    public String getId() { return Id; }

    public String getFullName() {
        return names + " " + lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getCellphone() {
        return cellphone;
    }

    public String getGender() { return gender; }

    public String getCountry() { return country; }

    public String getAddress() { return address; }

    public Date getDate() { return date; }

    public Lender(String Id, String email, String password, String document, String names, String lastname, String gender, String country, String address, String cellphone, Date date, Boolean state, Boolean active) {
        this.Id = Id;
        this.email = email;
        this.password = password;
        this.document = document;
        this.names = names;
        this.lastname = lastname;
        this.gender = gender;
        this.country = country;
        this.address = address;
        this.cellphone = cellphone;
        this.date = date;
        this.state = state;
        this.active = active;
    }
}