package com.fuentescreations.simplerecyclerviewexample.domain.dogs

import com.fuentescreations.simplerecyclerviewexample.application.ResultState

interface DogsRepo {
    suspend fun getDogs(): ResultState<List<String>>
}