package com.fuentescreations.simplerecyclerviewexample.repository.models

import com.google.gson.annotations.SerializedName

data class Photos(
    @SerializedName("id") val id:Int=-1,
    @SerializedName("albumId") val albumId:Int=-1,
    @SerializedName("title") val title:String="",
    @SerializedName("url") val imageUrl:String="",
    @SerializedName("thumbnailUrl") val thumbnailUrl:String=""
)