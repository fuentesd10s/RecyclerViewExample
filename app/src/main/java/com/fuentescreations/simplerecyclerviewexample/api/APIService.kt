package com.fuentescreations.simplerecyclerviewexample.api

import com.fuentescreations.simplerecyclerviewexample.repository.models.Movie
import com.fuentescreations.simplerecyclerviewexample.repository.models.Photos
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("photos")
    fun getPhotos(): Call<List<Photos>>

    @GET("movie/upcoming")
     fun getUpcomingMovies(@Query("api_key") apiKey: String): List<Movie>

    @GET("movie/top_rated")
     fun getTopRatedMovies(@Query("api_key") apiKey: String): List<Movie>

    @GET("movie/popular")
     fun getPopularMovies(@Query("api_key") apiKey: String): List<Movie>
}