package com.joserrfborges.githubrepositories.data.di

import com.joserrfborges.githubrepositories.data.RetrofitClient.createWebService
import com.joserrfborges.githubrepositories.data.api.GithubRepositoriesApi
import com.joserrfborges.githubrepositories.data.repositories.GithubRepositoriesRepository
import com.joserrfborges.githubrepositories.data.repositories.GithubRepositoriesRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    single {
        createWebService<GithubRepositoriesApi>()
    }

    factory<GithubRepositoriesRepository> {
        GithubRepositoriesRepositoryImpl(
            api = get()
        )
    }
}
