package com.example.practicapmdm_movies.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofit;

    /* Indique su API Key de themoviedb.org */
    private static final String API_KEY = "0201252d15312c7ed74c5f83d8f6ea15";
    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    /*
    Si no exite la instancia retrofit la crea, indicando que se va a convertir un fichero JSON
    */
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static String getApiKey () {
        return API_KEY;
    }
}