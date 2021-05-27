package com.example.fastcredits.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EnableLender {
    @SerializedName("prestamistaId")
    @Expose
    private String prestamistaId;

    public EnableLender(String prestamistaId) {
        super();

        this.prestamistaId = prestamistaId;
    }
}
