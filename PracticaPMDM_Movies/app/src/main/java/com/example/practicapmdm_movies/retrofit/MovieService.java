package com.example.practicapmdm_movies.retrofit;

import com.example.practicapmdm_movies.Creditos.Creditos;
import com.example.practicapmdm_movies.CreditosSeries.CreditosSeries;
import com.example.practicapmdm_movies.Generos.Generos;
import com.example.practicapmdm_movies.GenerosSeries.GenerosSeries;
import com.example.practicapmdm_movies.Series.Result;
import com.example.practicapmdm_movies.Series.Series;
import com.example.practicapmdm_movies.models.MovieFeed;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {

    @GET("movie/top_rated")
    Call<MovieFeed> getTopRated(@Query("api_key") String apiKey, @Query("language") String language);

    @GET("movie/popular")
    Call<MovieFeed> getLatest(@Query("api_key") String apiKey, @Query("language") String language);

    @GET("movie/{id}/credits")
    Call<Creditos> getCreditos(@Path("id") String id_movie, @Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<Generos> getGeneros(@Path("id") String id_movie,@Query("api_key") String apiKey, @Query("language") String language);

    @GET("tv/top_rated")
    Call<Series> getSeriestop_rated(@Query("api_key") String apiKey, @Query("language") String language);

    @GET("tv/popular")
    Call<Series> getSeriesPopular(@Query("api_key") String apiKey, @Query("language") String language);


    @GET("tv/{tv_id}/credits")
    Call<CreditosSeries> getSeriesCreditos(@Path("tv_id") String id_movie, @Query("api_key") String apiKey, @Query("language") String language);

    @GET("tv/{id}")
    Call<GenerosSeries> getGenerosSeries(@Path("id") String id_movie, @Query("api_key") String apiKey, @Query("language") String language);








}