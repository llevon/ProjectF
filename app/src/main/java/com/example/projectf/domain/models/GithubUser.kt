package com.example.projectf.domain.models

data class GithubUser(
    val login: String,
    val id: Int,
    val avatarUrl: String,
    val name: String,
    val email: String
)
