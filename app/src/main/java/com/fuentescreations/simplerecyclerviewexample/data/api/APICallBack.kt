package com.fuentescreations.simplerecyclerviewexample.data.api

interface APICallBack {
    fun onSuccess()
    fun onFailure(error:String)
}