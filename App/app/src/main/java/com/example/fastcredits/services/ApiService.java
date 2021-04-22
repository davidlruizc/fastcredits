package com.example.fastcredits.services;

import retrofit2.http.GET;

public interface ApiService {
    @GET("countries")
    Call<DiseasesResponse> getCountries();
}
