package com.example.projectf.domain.models

data class Repo(
    val id: Long,
    val name: String,
    val full_name: String,
    val private: Boolean,
    val owner: GithubUser,
    val description: String?,
    val html_url: String
)