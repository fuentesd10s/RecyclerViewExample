package com.fuentescreations.simplerecyclerviewexample.domain.userProfile

import com.fuentescreations.simplerecyclerviewexample.application.ResultState
import com.fuentescreations.simplerecyclerviewexample.data.models.UserProfile

interface UserProfileRepo {
    suspend fun getUserProfiles(): ResultState<List<UserProfile>>
}