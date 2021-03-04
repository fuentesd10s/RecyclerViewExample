package com.fuentescreations.simplerecyclerviewexample.api

import com.fuentescreations.simplerecyclerviewexample.repository.models.Dogs
import com.fuentescreations.simplerecyclerviewexample.repository.models.Photos
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {

    @GET("photos")
    fun getPhotos(): Call<List<Photos>>

    @GET("image/random/10")
    fun getRandomDogs(): Call<Dogs>
}