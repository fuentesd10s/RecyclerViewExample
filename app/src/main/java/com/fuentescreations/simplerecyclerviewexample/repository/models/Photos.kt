package com.fuentescreations.simplerecyclerviewexample.repository.models

import com.google.gson.annotations.SerializedName

data class Photos(
    val id:Int=-1,
    val albumId:Int=-1,
     val title:String="",
    @SerializedName("url")
    val imageUrl:String="",
    val thumbnailUrl:String=""
)