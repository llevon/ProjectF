package com.example.projectf.data.mappers

import com.example.projectf.GithubUser
import com.example.projectf.data.dtos.GithubUserDto

fun GithubUserDto.toGithubUser() = GithubUser(login, id, avatarUrl, name, email)