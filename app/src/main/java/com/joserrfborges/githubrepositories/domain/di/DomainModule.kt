package com.joserrfborges.githubrepositories.domain.di

import com.joserrfborges.githubrepositories.domain.usecases.GithubRepositoriesUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        GithubRepositoriesUseCase(
            githubRepositoriesRepository = get()
        )
    }
}