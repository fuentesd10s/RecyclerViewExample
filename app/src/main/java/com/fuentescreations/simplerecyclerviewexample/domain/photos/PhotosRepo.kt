package com.fuentescreations.simplerecyclerviewexample.domain.photos

import com.fuentescreations.simplerecyclerviewexample.application.ResultState
import com.fuentescreations.simplerecyclerviewexample.data.models.Photos

interface PhotosRepo {
    suspend fun getPhotos(): ResultState<List<Photos>>
}