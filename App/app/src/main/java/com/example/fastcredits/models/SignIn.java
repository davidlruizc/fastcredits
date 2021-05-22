package com.example.fastcredits.models;

public class SignIn {
    private String email;
    private String password;
    private int role;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getRole() { return role; }

    public SignIn(String email, String password, int role) {
        super();

        this.email = email;
        this.password = password;
        this.role = role;
    }
}
