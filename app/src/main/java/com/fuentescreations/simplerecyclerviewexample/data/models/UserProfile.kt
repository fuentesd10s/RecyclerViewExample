package com.fuentescreations.simplerecyclerviewexample.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserProfile(
        val id:Int=-1,
        val nickname:String="",
        @SerializedName("github_profile")
        val githubProfile:String="",
        @SerializedName("twitter_profile")
        val twitterProfile:String="",
        @SerializedName("contributions_count")
        val contributionsCount:Int=0,
        val organisations: List<Organisations> = listOf()
) : Serializable

data class Organisations(
        val login:String="",
        @SerializedName("avatar_url")
        val avatarUrl:String="",
        val link:String=""
) : Serializable