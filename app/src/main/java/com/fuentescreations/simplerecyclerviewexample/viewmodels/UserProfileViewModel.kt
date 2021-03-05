package com.fuentescreations.simplerecyclerviewexample.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fuentescreations.simplerecyclerviewexample.api.APICallBack
import com.fuentescreations.simplerecyclerviewexample.api.APIService
import com.fuentescreations.simplerecyclerviewexample.application.AppConstans
import com.fuentescreations.simplerecyclerviewexample.repository.models.UserProfile
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserProfileViewModel:ViewModel() {

    private val userProfilesLiveData=MutableLiveData<List<UserProfile>>()

    private fun setUserProfileList(userProfiles:List<UserProfile>){
        userProfilesLiveData.value=userProfiles
    }

    fun getUserProfilesLiveData(): LiveData<List<UserProfile>> {
        return userProfilesLiveData
    }

    fun getUserProfilesList(callBack:APICallBack){
        val mRetrofit = Retrofit
                .Builder()
                .baseUrl(AppConstans.BASE_URL_PROFILES)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIService::class.java)

        mRetrofit
                .getUserProfiles("users.json")
                .enqueue(object : Callback<List<UserProfile>>{
                    override fun onResponse(call: Call<List<UserProfile>>, response: Response<List<UserProfile>>) {
                        if (response.isSuccessful){
                            setUserProfileList(response.body()!!)
                            callBack.onSuccess()
                        }
                    }

                    override fun onFailure(call: Call<List<UserProfile>>, t: Throwable) {
                        callBack.onFailure(t.toString())
                    }

                })
    }
}