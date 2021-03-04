package com.fuentescreations.simplerecyclerviewexample.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fuentescreations.simplerecyclerviewexample.api.APIService
import com.fuentescreations.simplerecyclerviewexample.api.APICallBack
import com.fuentescreations.simplerecyclerviewexample.application.AppConstans
import com.fuentescreations.simplerecyclerviewexample.repository.models.Photos
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PhotosViewModel : ViewModel() {

    private val listPhotosLiveData = MutableLiveData<List<Photos>>()

    private fun setListPhotos(listPhotos: List<Photos>) {
        listPhotosLiveData.value = listPhotos
    }

    fun getListPhotosLiveData(): LiveData<List<Photos>> {
        return listPhotosLiveData
    }

    fun getListPhotos(callBack: APICallBack) {

        val mRetrofit = Retrofit
                .Builder()
                .baseUrl(AppConstans.BASE_URL_PHOTOS)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIService::class.java)

        mRetrofit
                .getPhotos()
                .enqueue(object : Callback<List<Photos>> {
                    override fun onResponse(
                            call: Call<List<Photos>>,
                            response: Response<List<Photos>>
                    ) {
                        if (response.isSuccessful) {

                            setListPhotos(response.body()!!)
                            callBack.onSuccess()
                        }
                    }

                    override fun onFailure(call: Call<List<Photos>>, t: Throwable) {
                        setListPhotos(mutableListOf())
                        callBack.onFailure(t.toString())
                    }
                })
    }
}