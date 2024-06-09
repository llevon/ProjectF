package com.example.projectf.data.repositries

import com.example.projectf.data.mappers.toGithubUser
import com.example.projectf.data.mappers.toRepositories
import com.example.projectf.data.network.ApiService
import com.example.projectf.domain.models.GithubUser
import com.example.projectf.domain.repositories.AllUsersRepository
import com.example.projectf.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class AllUsersRepositoryImpl(
    private val apiService: ApiService
): AllUsersRepository {
    override fun getAllUsersList(): Flow<Resource<List<GithubUser>>> {
        return flow {
            emit(Resource.Loading)
            val response = apiService.getAllUsers()
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(Resource.Success(it.toGithubUser()))
                }
            }
            else {
                emit(Resource.Error(
                    Exception(
                    response.errorBody()?.byteStream()?.bufferedReader()?.readText())
                ))
            }

        }
    }

}