package com.example.mobile_travelreservation.remote;

import com.example.mobile_travelreservation.model.Traveler;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TravelerService {
    @GET("traveler/")
    Call<List<Traveler>> getTraveler();

    @POST("traveler/")
    Call<Traveler> addTraveler(@Body Traveler traveler);

    @POST("login/")
    Call<Traveler> login(@Body String Email, String Password);

    @PUT("traveler/{id}")
    Call<Traveler> updateTraveler(@Path("id") String id, @Body Traveler traveler);

    @DELETE("traveler/{id}")
    Call<Traveler> deleteTraveler(@Path("id") String id);

}
