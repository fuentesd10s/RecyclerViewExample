package com.fuentescreations.simplerecyclerviewexample.viewmodels

import androidx.lifecycle.*
import com.fuentescreations.simplerecyclerviewexample.application.ResultState
import com.fuentescreations.simplerecyclerviewexample.domain.userProfile.UserProfileRepo
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class UserProfileViewModel(private val repo:UserProfileRepo) : ViewModel() {

    fun getUserProfiles() = liveData(Dispatchers.IO){
        emit(ResultState.Loading())

        try {
            emit(repo.getUserProfiles())
        }catch (e:Exception){
            emit(ResultState.Failure(e))
        }
    }
}

class UserProfileViewModelFactory(private val repo:UserProfileRepo):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserProfileViewModel(repo) as T
    }
}