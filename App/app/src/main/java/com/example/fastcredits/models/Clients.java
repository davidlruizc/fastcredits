package com.example.fastcredits.models;


public class Clients {
    private String email;
    private String password;
    private String document;
    private String names;
    private String lastname;
    private String gender;
    private String country;
    private String address;
    private String cellphone;

    public Clients(String email, String password, String document, String names, String lastname, String gender, String country, String address, String cellphone) {
        super();
        this.email = email;
        this.password = password;
        this.document = document;
        this.names = names;
        this.lastname = lastname;
        this.gender = gender;
        this.country = country;
        this.address = address;
        this.cellphone = cellphone;
    }
}
