package com.example.projectf.data.dtos

import com.google.gson.annotations.SerializedName

data class GithubUserDto(
    val login: String,
    val id: Int,
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    val type: String,
)
