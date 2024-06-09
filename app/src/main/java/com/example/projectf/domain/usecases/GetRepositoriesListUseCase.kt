package com.example.projectf.domain.usecases

import com.example.projectf.domain.models.Repositories
import com.example.projectf.domain.repositories.RepositoriesListRepository
import com.example.projectf.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetRepositoriesListUseCase(
    private val repositoriesListRepository: RepositoriesListRepository
) {
    operator fun invoke(): Flow<Resource<List<Repositories>>>{
        return repositoriesListRepository.getRepositoriesList()
    }
}