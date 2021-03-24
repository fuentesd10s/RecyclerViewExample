package com.fuentescreations.simplerecyclerviewexample.data.remote

import com.fuentescreations.simplerecyclerviewexample.application.AppConstans
import com.fuentescreations.simplerecyclerviewexample.application.ResultState
import com.fuentescreations.simplerecyclerviewexample.data.api.APIService
import com.fuentescreations.simplerecyclerviewexample.data.models.UserProfile
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserProfilesDataSource {

    suspend fun getUserProfiles(): ResultState<List<UserProfile>>{
        val userProfiles = mutableListOf<UserProfile>()

        val mRetrofit = Retrofit
            .Builder()
            .baseUrl(AppConstans.BASE_URL_PROFILES)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService::class.java)

        userProfiles.addAll(mRetrofit.getUserProfiles())

        return ResultState.Success(userProfiles)
    }
}