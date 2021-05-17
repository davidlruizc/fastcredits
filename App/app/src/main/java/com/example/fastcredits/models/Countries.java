package com.example.fastcredits.models;

public class Countries {
    private String _id;
    private String name;
    private String code;

    public String getName() {
        return name;
    }

    public String getId() {
        return _id;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return name;
    }
}
