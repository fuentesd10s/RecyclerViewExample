package com.fuentescreations.simplerecyclerviewexample.data.remote

import com.fuentescreations.simplerecyclerviewexample.application.AppConstans
import com.fuentescreations.simplerecyclerviewexample.application.ResultState
import com.fuentescreations.simplerecyclerviewexample.data.api.APIService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DogsDataSource {
    suspend fun getDogs(): ResultState<List<String>> {
        val dogs = mutableListOf<String>()

        val mRetrofit = Retrofit
            .Builder()
            .baseUrl(AppConstans.BASE_URL_DOGS)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService::class.java)

        dogs.addAll(mRetrofit.getRandomDogs2().images)

        return ResultState.Success(dogs)
    }
}