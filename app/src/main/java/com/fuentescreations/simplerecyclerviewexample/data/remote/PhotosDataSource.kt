package com.fuentescreations.simplerecyclerviewexample.data.remote

import com.fuentescreations.simplerecyclerviewexample.data.api.APIService
import com.fuentescreations.simplerecyclerviewexample.application.AppConstans
import com.fuentescreations.simplerecyclerviewexample.application.ResultState
import com.fuentescreations.simplerecyclerviewexample.data.models.Photos
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PhotosDataSource {
    suspend fun getLatestPosts(): ResultState<List<Photos>>{
        val photosList = mutableListOf<Photos>()

        val mRetrofit = Retrofit
            .Builder()
            .baseUrl(AppConstans.BASE_URL_PHOTOS)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService::class.java)

        photosList.addAll(mRetrofit.getPhotos())

        return ResultState.Success(photosList)
    }
}