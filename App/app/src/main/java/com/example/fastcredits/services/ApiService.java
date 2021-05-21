package com.example.fastcredits.services;

import com.example.fastcredits.models.ApiResponse;
import com.example.fastcredits.models.Countries;
import com.example.fastcredits.models.Lender;
import com.example.fastcredits.models.SignIn;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("countries")
    Call<ArrayList<Countries>> getCountries();

    @POST("prestamista/signUp")
    Call<ApiResponse> signUpLender(@Body Lender lender);

    @POST("users/signIn")
    Call<ApiResponse> singInUser(@Body SignIn signIn);
}
