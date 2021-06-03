package com.example.fastcredits.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.Date;

public class Credit {

    @SerializedName("client")
    @Expose
    private String client;

    @SerializedName("_id")
    @Expose
    private String id;

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
    private float amount;

    @SerializedName("interest")
    @Expose
    private float interest;

    @SerializedName("fee")
    @Expose
    private float fee;

    @SerializedName("date")
    @Expose
    private Date date;

    public String getId() { return id; }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public int getPeriodicity() {
        return periodicity;
    }

    public float getAmount() {
        return amount;
    }

    public float getInterest() {
        return interest;
    }

    public float getFee() {
        return fee;
    }

    public Date getDate() {
        return date;
    }

    public Credit(String id, String paymentMethod, int periodicity, float amount, float interest, float fee, Date date){
        this.id = id;
        this.paymentMethod = paymentMethod;
        this.periodicity = periodicity;
        this.amount = amount;
        this.interest = interest;
        this.fee = fee;
        this.date = date;
    }
}
