package com.fuentescreations.simplerecyclerviewexample.viewmodels

import androidx.lifecycle.*
import com.fuentescreations.simplerecyclerviewexample.application.ResultState
import com.fuentescreations.simplerecyclerviewexample.data.models.Photos
import com.fuentescreations.simplerecyclerviewexample.domain.photos.PhotosRepo
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class PhotosViewModel(private val repo: PhotosRepo) : ViewModel() {

    private val loadTrigger = MutableLiveData(Unit)

    fun refreshData() {
        loadTrigger.value = Unit
    }

    val getPhotos: LiveData<ResultState<List<Photos>>> = loadTrigger.switchMap {

        liveData(Dispatchers.IO) {

            emit(ResultState.Loading())

            try {
                emit(repo.getPhotos())
            } catch (e: Exception) {
                emit(ResultState.Failure(e))
            }
        }
    }
}

class PhotosViewModelFactory(private val repo: PhotosRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PhotosViewModel(repo) as T
    }
}