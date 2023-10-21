package com.example.mobile_travelreservation.remote;

public class APIUtils {
    private APIUtils(){
    };

    public static final String API_URL = "https://192.168.1.3:7015/api/";

    public static TravelerService getTravelerService(){
        return RetrofitClient.getClient(API_URL).create(TravelerService.class);
    }
}
