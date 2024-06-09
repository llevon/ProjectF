package com.example.projectf.data.di

import com.example.projectf.data.network.ApiService
import com.example.projectf.data.repositries.AllUsersRepositoryImpl
import com.example.projectf.data.repositries.RepositoriesListRepositoryImpl
import com.example.projectf.domain.repositories.AllUsersRepository
import com.example.projectf.domain.repositories.RepositoriesListRepository
import com.example.projectf.domain.usecases.GetRepositoriesListUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.koin.dsl.module
import org.koin.dsl.single
import javax.inject.Singleton

/*val dataModule = module {
    single<RepositoriesListRepository> {
        RepositoriesListRepositoryImpl()
    }
}*/

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideRepositoriesRepository(apiService: ApiService): RepositoriesListRepository =
        RepositoriesListRepositoryImpl(apiService)

    @Provides
    @Singleton
    fun provideAllUsersRepository(apiService: ApiService): AllUsersRepository =
        AllUsersRepositoryImpl(apiService)
}
/*
@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule{
    @Binds
    abstract fun bindRepositoriesListRepository(impl:RepositoriesListRepositoryImpl):RepositoriesListRepository
}*/
