package com.example.projectf.domain.repositories

import com.example.projectf.GithubUser
import com.example.projectf.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface GithubUserRepository {
    fun getGithubUser(): Flow<Resource<GithubUser>>
}