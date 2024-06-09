package com.example.projectf.data.mappers


import com.example.projectf.data.dtos.GithubUserDto
import com.example.projectf.data.dtos.RepositoriesDto
import com.example.projectf.domain.models.GithubUser
import com.example.projectf.domain.models.Repositories

fun GithubUserDto.toGithubUser() = GithubUser(login, id, avatarUrl, type)
fun List<GithubUserDto>.toGithubUser(): List<GithubUser> {
    return this.map { it.toGithubUser() }
}