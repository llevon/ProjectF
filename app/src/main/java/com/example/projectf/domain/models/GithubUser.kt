package com.example.projectf.domain.models

import com.google.gson.annotations.SerializedName

data class GithubUser(
    val login: String,
    val id: Int,
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    val type: String,
)
