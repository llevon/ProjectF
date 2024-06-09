package com.example.projectf.data.network

import com.example.projectf.data.dtos.GithubUserDto
import com.example.projectf.data.dtos.RepositoriesDto
import com.example.projectf.domain.models.GithubUser
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {
    @GET("users/llevon/repos")
    suspend fun getRepositories(): Response<List<RepositoriesDto>>

    @GET ("users")
    suspend fun getAllUsers():Response<List<GithubUserDto>>

}