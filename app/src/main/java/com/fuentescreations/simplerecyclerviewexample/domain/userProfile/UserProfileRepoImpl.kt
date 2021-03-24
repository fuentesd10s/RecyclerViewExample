package com.fuentescreations.simplerecyclerviewexample.domain.userProfile

import com.fuentescreations.simplerecyclerviewexample.application.ResultState
import com.fuentescreations.simplerecyclerviewexample.data.models.UserProfile
import com.fuentescreations.simplerecyclerviewexample.data.remote.UserProfilesDataSource

class UserProfileRepoImpl(private val dataSource: UserProfilesDataSource) : UserProfileRepo {
    override suspend fun getUserProfiles(): ResultState<List<UserProfile>> {
        return dataSource.getUserProfiles()
    }
}