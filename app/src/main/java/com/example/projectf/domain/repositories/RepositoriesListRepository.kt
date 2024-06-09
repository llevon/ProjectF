package com.example.projectf.domain.repositories

import com.example.projectf.domain.models.Repositories
import com.example.projectf.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface RepositoriesListRepository {
    fun getRepositoriesList(): Flow<Resource<List<Repositories>>>
}