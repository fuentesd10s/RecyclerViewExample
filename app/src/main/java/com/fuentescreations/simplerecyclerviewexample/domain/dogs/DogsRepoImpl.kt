package com.fuentescreations.simplerecyclerviewexample.domain.dogs

import com.fuentescreations.simplerecyclerviewexample.application.ResultState
import com.fuentescreations.simplerecyclerviewexample.data.models.Dogs
import com.fuentescreations.simplerecyclerviewexample.data.remote.DogsDataSource

class DogsRepoImpl(private val dataSource: DogsDataSource) : DogsRepo {
    override suspend fun getDogs(): ResultState<List<String>> {
        return dataSource.getDogs()
    }
}