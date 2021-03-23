package com.fuentescreations.simplerecyclerviewexample.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.fuentescreations.simplerecyclerviewexample.application.ResultState
import com.fuentescreations.simplerecyclerviewexample.domain.photos.PhotosRepo
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class PhotosViewModel(private val repo: PhotosRepo) : ViewModel() {

    /*
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
*/

    fun fetchLatestPhotos() = liveData(Dispatchers.IO) {

        emit(ResultState.Loading())

        try {

            emit(repo.getLatestPhotos())

        } catch (e: Exception) {

            emit(ResultState.Failure(e))

        }
    }
}

class PhotosViewModelFactory(private val repo: PhotosRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PhotosViewModel(repo) as T
    }
}