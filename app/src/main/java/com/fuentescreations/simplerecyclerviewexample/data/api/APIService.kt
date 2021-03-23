package com.fuentescreations.simplerecyclerviewexample.data.api

import com.fuentescreations.simplerecyclerviewexample.data.models.Dogs
import com.fuentescreations.simplerecyclerviewexample.data.models.Photos
import com.fuentescreations.simplerecyclerviewexample.data.models.UserProfile
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {

    @GET("photos")
    fun getPhotos(): Call<List<Photos>>

    @GET("photos")
    suspend fun getPhotos2(): List<Photos>

    @GET("image/random/10")
    fun getRandomDogs(): Call<Dogs>

    @GET("image/random/10")
    suspend fun getRandomDogs2(): Dogs

    @GET
    fun getUserProfiles(@Url url:String): Call<List<UserProfile>>
}