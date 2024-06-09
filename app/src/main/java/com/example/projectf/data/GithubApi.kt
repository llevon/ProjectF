package com.example.projectf.data
import com.example.projectf.domain.models.GithubUser
import com.example.projectf.domain.models.Repo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface GithubApi {
    @GET("user")
    fun getUser(@Header("Authorization") authHeader: String): Call<GithubUser>

}