package com.example.projectf.domain.di

import com.example.projectf.domain.repositories.AllUsersRepository
import com.example.projectf.domain.repositories.RepositoriesListRepository
import com.example.projectf.domain.usecases.AllUsersUseCase
import com.example.projectf.domain.usecases.GetRepositoriesListUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.koin.dsl.module

/*val domainModule = module {
    factory<GetRepositoriesListUseCase> {
        GetRepositoriesListUseCase(repositoriesListRepository = get())
    }
}*/
@Module
@InstallIn(SingletonComponent::class)
object DomainModule{
    @Provides
    fun providesGetRepositoriesUseCase(repositoriesListRepository: RepositoriesListRepository) = GetRepositoriesListUseCase(repositoriesListRepository)

    @Provides
    fun providesGetAllUsersUseCase(allUsersRepository: AllUsersRepository) = AllUsersUseCase(allUsersRepository)
}


/*
@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule{
    @Binds
    abstract fun bindGetRepositoriesUseCase(impl:GetRepositoriesListUseCase):GetRepositoriesListUseCase
}*/
