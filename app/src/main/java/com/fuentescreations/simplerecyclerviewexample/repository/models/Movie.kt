package com.fuentescreations.simplerecyclerviewexample.repository.models

import com.google.gson.annotations.SerializedName

data class Movie (
        val id: Int = -1,
        val adult: Boolean = false,
        @SerializedName("backdrop_path")
        val backdrop_path: String = "",
        @SerializedName("original_title")
        val original_title: String = "",
        @SerializedName("original_language")
        val original_language: String = "",
        val overview: String = "",
        val popularity: Double = -1.0,
        @SerializedName("poster_path")
        val posterPath: String = "",
        @SerializedName("release_date")
        val releaseDate: String = "",
        val title: String = "",
        val video: Boolean = false,
        @SerializedName("vote_average")
        val voteAverage: Double = -1.0,
        @SerializedName("voteCount")
        val voteCount: Int = -1,
        @SerializedName("movie_type")
        val movieType: String = ""
        )