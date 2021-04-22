package com.example.fastcredits.services;

import com.example.fastcredits.models.Countries;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("countries")
    Call<ArrayList<Countries>> getCountries();
}
