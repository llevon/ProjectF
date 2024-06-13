package com.example.projectf.data.network

import com.example.projectf.data.dtos.GithubUserDto
import com.example.projectf.data.dtos.RepositoriesDto
import com.example.projectf.domain.models.GithubUser
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {
    @GET("users/{username}/repos")
    suspend fun getRepositories(@Path("username") username: String): Response<List<RepositoriesDto>>


    @GET ("users")
    suspend fun getAllUsers():Response<List<GithubUserDto>>

}