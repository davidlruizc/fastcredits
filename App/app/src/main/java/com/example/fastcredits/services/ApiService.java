package com.example.fastcredits.services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("countries")
    Call<List> getCountries();
}
