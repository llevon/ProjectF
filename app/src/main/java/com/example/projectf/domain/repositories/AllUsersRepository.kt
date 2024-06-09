package com.example.projectf.domain.repositories

import com.example.projectf.domain.models.GithubUser
import com.example.projectf.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface AllUsersRepository {
    fun getAllUsersList(): Flow<Resource<List<GithubUser>>>
}