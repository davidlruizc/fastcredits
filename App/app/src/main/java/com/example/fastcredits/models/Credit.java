package com.example.fastcredits.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Credit {
    @SerializedName("client")
    @Expose
    private String client;

    @SerializedName("paymentMethod")
    @Expose
    private String paymentMethod;

    @SerializedName("periodicity")
    @Expose
    private int periodicity;

    @SerializedName("amount")
    @Expose
    private int amount;

    @SerializedName("interest")
    @Expose
    private int interest;

    @SerializedName("fee")
    @Expose
    private double fee;

    public Credit(String client, String paymentMethod, int periodicity, int amount, int interest, double fee) {
        super();

        this.client = client;
        this.paymentMethod = paymentMethod;
        this.periodicity = periodicity;
        this.amount = amount;
        this.interest = interest;
        this.fee = fee;
    }
}
