package com.fuentescreations.simplerecyclerviewexample.data.models

import com.google.gson.annotations.SerializedName

data class Dogs(
        val status: String="",
        @SerializedName("message")
        val images: List<String> = listOf()
)