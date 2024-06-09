package com.example.projectf.domain.usecases

import com.example.projectf.domain.models.GithubUser
import com.example.projectf.domain.repositories.GithubUserRepository
import com.example.projectf.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetLoginUseCase(
    private val githubUserRepository: GithubUserRepository
) {
    operator fun invoke(): Flow<Resource<GithubUser>> {
        return githubUserRepository.getGithubUser()
    }
}