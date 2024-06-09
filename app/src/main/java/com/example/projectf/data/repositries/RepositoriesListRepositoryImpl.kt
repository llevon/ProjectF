package com.example.projectf.data.repositries

import com.example.projectf.data.dtos.RepositoriesDto
import com.example.projectf.data.mappers.toRepositories
import com.example.projectf.data.network.ApiService
import com.example.projectf.domain.models.Repositories
import com.example.projectf.domain.repositories.RepositoriesListRepository
import com.example.projectf.domain.utils.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class RepositoriesListRepositoryImpl(
    private val apiService: ApiService
) : RepositoriesListRepository {
    override fun getRepositoriesList(): Flow<Resource<List<Repositories>>> {
        return flow {
            emit(Resource.Loading)
            val response = apiService.getRepositories()
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(Resource.Success(it.toRepositories()))
                }
            }
                else {
                    emit(Resource.Error(Exception(
                        response.errorBody()?.byteStream()?.bufferedReader()?.readText())))
                }

            }
        }
    }


