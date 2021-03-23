package com.fuentescreations.simplerecyclerviewexample.application

sealed class ResultState<out T> {
    class Loading<out T>:ResultState<T>()

    data class Success<out T>(val data: T):ResultState<T>()

    data class Failure(val exception:Exception):ResultState<Nothing>()
}