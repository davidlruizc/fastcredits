package com.example.fastcredits.services;

import com.example.fastcredits.models.Admin;
import com.example.fastcredits.models.ApiResponse;
import com.example.fastcredits.models.Countries;
import com.example.fastcredits.models.Clients;
import com.example.fastcredits.models.EnableDisableUser;
import com.example.fastcredits.models.EnableLender;
import com.example.fastcredits.models.Lenders;
import com.example.fastcredits.models.SetEnableDisableUser;
import com.example.fastcredits.models.SignIn;
import com.example.fastcredits.models.Users;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("countries")
    Call<ArrayList<Countries>> getCountries();

    @POST("prestamista/signUp")
    Call<ApiResponse> signUpLender(@Body Clients clients);

    @POST("users/signIn")
    Call<ApiResponse> singInUser(@Body SignIn signIn);

    @POST("users/signUp")
    Call<ApiResponse> signUpClient(@Body Clients clients);

    @POST("rutero/signUp")
    Call<ApiResponse> signUpRouter(@Body Clients clients);

    @POST("admins/signUp")
    Call<ApiResponse> signUpAdmin(@Body Admin admin);

    @GET("admins/pendingPrestamistas")
    Call<Lenders> getPendingLenders();

    @GET("admins/allPrestamistas")
    Call<Lenders> getAllLenders();

    @POST("admins/enablePrestamista")
    Call<ApiResponse> enableLender(@Body EnableLender enableLender);

    @GET("admins/allUsers")
    Call<Users> getAllUsers();

    @POST("admins/enableDisableUser")
    Call<EnableDisableUser> enableDisableUser(@Body SetEnableDisableUser setEnableDisableUser);
}
