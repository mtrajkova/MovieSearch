package com.labs.mpip.lab2.services;

import com.labs.mpip.lab2.models.Movie;
import com.labs.mpip.lab2.models.MovieResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OmdbApiService {
    @GET("/")
    Call<MovieResponse> getMovies(@Query("s") String title, @Query("apikey") String apikey);

    @GET("/")
    Call<MovieResponse> getMovieByImdbId(@Query("i") String imdbId, @Query("apikey") String apikey);
}
