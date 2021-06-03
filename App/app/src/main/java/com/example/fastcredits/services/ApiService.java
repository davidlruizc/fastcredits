package com.example.fastcredits.services;

import com.example.fastcredits.models.Admin;
import com.example.fastcredits.models.AllCredits;
import com.example.fastcredits.models.AllUsers;
import com.example.fastcredits.models.ApiResponse;
import com.example.fastcredits.models.Countries;
import com.example.fastcredits.models.Clients;
import com.example.fastcredits.models.Credit;
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
import retrofit2.http.Path;
import retrofit2.http.Query;

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

    @GET("admins/allUsersAccounts")
    Call<AllUsers> getAllUsersAccounts();

<<<<<<< HEAD
    @POST("prestamista/createCredit")
    Call<ApiResponse> createCredit(@Body Credit credit);
=======
    @GET("prestamista/getCredits/{id}")
    Call<AllCredits> getCredits(@Path("id") String id);

>>>>>>> 7603965205a5c6433c8066ec5b981c3f4441bdcb
}
