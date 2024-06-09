package com.example.projectf.data.mappers

import com.example.projectf.data.dtos.RepositoriesDto
import com.example.projectf.domain.models.Repositories

fun RepositoriesDto.toRepositories(): Repositories {
    return Repositories(
        name = this.name,
        description = this.description,
        private = this.private
    )
}

fun List<RepositoriesDto>.toRepositories(): List<Repositories> {
    return this.map { it.toRepositories() }
}

