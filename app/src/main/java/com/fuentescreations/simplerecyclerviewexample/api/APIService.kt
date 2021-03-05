package com.fuentescreations.simplerecyclerviewexample.api

import com.fuentescreations.simplerecyclerviewexample.repository.models.Dogs
import com.fuentescreations.simplerecyclerviewexample.repository.models.Photos
import com.fuentescreations.simplerecyclerviewexample.repository.models.UserProfile
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {

    @GET("photos")
    fun getPhotos(): Call<List<Photos>>

    @GET("image/random/10")
    fun getRandomDogs(): Call<Dogs>

    @GET
    fun getUserProfiles(@Url url:String): Call<List<UserProfile>>
}