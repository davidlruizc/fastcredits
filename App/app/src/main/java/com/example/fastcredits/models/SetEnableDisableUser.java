package com.example.fastcredits.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class SetEnableDisableUser {
    @SerializedName("userId")
    @Expose
    private String userId;

    @SerializedName("rol")
    @Expose
    private String rol;

    @SerializedName("state")
    @Expose
    private Boolean state;

    public SetEnableDisableUser(String userId, String rol, Boolean state) {
        super();

        this.userId = userId;
        this.rol = rol;
        this.state = state;
    }
}
