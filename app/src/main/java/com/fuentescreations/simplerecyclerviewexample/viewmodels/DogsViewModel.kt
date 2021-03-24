package com.fuentescreations.simplerecyclerviewexample.viewmodels

import androidx.lifecycle.*
import com.fuentescreations.simplerecyclerviewexample.application.ResultState
import com.fuentescreations.simplerecyclerviewexample.domain.dogs.DogsRepo
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class DogsViewModel(private val repo: DogsRepo) : ViewModel() {

    fun getDogs() = liveData(Dispatchers.IO) {
        emit(ResultState.Loading())

        try {
            emit(repo.getDogs())
        } catch (e: Exception) {
            emit(ResultState.Failure(e))
        }
    }
}

class DogsViewModelFactory(private val repo: DogsRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DogsViewModel(repo) as T
    }
}