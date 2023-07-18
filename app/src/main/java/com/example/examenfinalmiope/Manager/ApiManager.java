package com.example.examenfinalmiope.Manager;

import com.example.examenfinalmiope.Service.HolidayService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {
    private static final String BASE_URL = "https://date.nager.at/api/v3/";
    private static final Gson gson = new GsonBuilder().setLenient().create();
    static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    public static HolidayService getHolidayService() {
        return retrofit.create(HolidayService.class);
    }
}
