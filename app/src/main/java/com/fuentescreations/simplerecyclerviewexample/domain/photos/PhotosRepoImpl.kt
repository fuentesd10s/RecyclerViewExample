package com.fuentescreations.simplerecyclerviewexample.domain.photos

import com.fuentescreations.simplerecyclerviewexample.application.ResultState
import com.fuentescreations.simplerecyclerviewexample.data.models.Photos
import com.fuentescreations.simplerecyclerviewexample.data.remote.PhotosDataSource

class PhotosRepoImpl(private val dataSource: PhotosDataSource) : PhotosRepo {

    override suspend fun getLatestPhotos(): ResultState<List<Photos>> {
        return dataSource.getLatestPosts()
    }

}