package com.fuentescreations.simplerecyclerviewexample.data.api

import com.fuentescreations.simplerecyclerviewexample.data.models.Dogs
import com.fuentescreations.simplerecyclerviewexample.data.models.Photos
import com.fuentescreations.simplerecyclerviewexample.data.models.UserProfile
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {

    @GET("photos")
    suspend fun getPhotos(): List<Photos>

    @GET("image/random/10")
    suspend fun getRandomDogs(): Dogs

    @GET
    suspend fun getUserProfiles(@Url url:String="users.json"): List<UserProfile>
}