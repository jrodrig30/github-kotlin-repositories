package com.joserrfborges.githubrepositories.presentation.di

import com.joserrfborges.githubrepositories.presentation.ui.githubRepositories.GithubRepositoriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val presentationModule = module{
    viewModel {
        GithubRepositoriesViewModel(
            githubRepositoriesUseCase = get()
        )
    }
}
