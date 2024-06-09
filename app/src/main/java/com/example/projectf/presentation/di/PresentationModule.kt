package com.example.projectf.presentation.di

import com.example.projectf.presentation.repositorylist.RepositoriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel<RepositoriesViewModel>(){
        RepositoriesViewModel(getRepositoriesListUseCase = get())
    }
}