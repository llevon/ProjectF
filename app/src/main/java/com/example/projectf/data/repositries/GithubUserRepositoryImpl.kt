package com.example.projectf.data.repositries

import com.example.projectf.domain.models.GithubUser
import com.example.projectf.domain.repositories.GithubUserRepository
import com.example.projectf.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GithubUserRepositoryImpl : GithubUserRepository {
    override fun getGithubUser(): Flow<Resource<GithubUser>> {
        return flow {
            emit(Resource.Loading)

            //emit(Resource.Success<>)

        }
    }
}