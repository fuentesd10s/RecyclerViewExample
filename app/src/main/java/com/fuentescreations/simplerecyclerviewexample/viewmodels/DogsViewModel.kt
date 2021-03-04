package com.fuentescreations.simplerecyclerviewexample.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fuentescreations.simplerecyclerviewexample.api.APICallBack
import com.fuentescreations.simplerecyclerviewexample.api.APIService
import com.fuentescreations.simplerecyclerviewexample.application.AppConstans
import com.fuentescreations.simplerecyclerviewexample.repository.models.Dogs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DogsViewModel : ViewModel() {

    private val listDogsLiveData = MutableLiveData<List<String>>()

    private fun setListDogs(listDogs: List<String>) {
        listDogsLiveData.value = listDogs
    }

    fun getListDogsLiveData(): LiveData<List<String>> {
        return listDogsLiveData
    }

    fun getListDogs(callBack: APICallBack) {
        val mRetrofit = Retrofit
                .Builder()
                .baseUrl(AppConstans.BASE_URL_DOGS)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIService::class.java)

        mRetrofit
                .getRandomDogs()
                .enqueue(object : Callback<Dogs>{
                    override fun onResponse(call: Call<Dogs>, response: Response<Dogs>) {
                        if (response.isSuccessful){
                            setListDogs(response.body()!!.images)
                            callBack.onSuccess()
                        }
                    }

                    override fun onFailure(call: Call<Dogs>, t: Throwable) {
                        setListDogs(listOf())
                        callBack.onFailure(t.toString())
                    }

                })
    }
}