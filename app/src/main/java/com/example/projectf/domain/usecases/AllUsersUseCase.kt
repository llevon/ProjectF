package com.example.projectf.domain.usecases

import com.example.projectf.domain.models.GithubUser
import com.example.projectf.domain.repositories.AllUsersRepository
import com.example.projectf.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

class AllUsersUseCase(
    private val allUsersRepository: AllUsersRepository
) {
    operator fun invoke(): Flow<Resource<List<GithubUser>>> {
        return allUsersRepository.getAllUsersList()
    }
}
