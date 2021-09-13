package com.joserrfborges.githubrepositories.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.joserrfborges.githubrepositories.data.api.GithubRepositoriesApi
import com.joserrfborges.githubrepositories.domain.model.Repository
import kotlinx.coroutines.flow.Flow

class GithubRepositoriesRepositoryImpl(
    private val api: GithubRepositoriesApi
) : GithubRepositoriesRepository{
    override fun getGithubRepositories(): Flow<PagingData<Repository>> {
        return Pager(
            config = PagingConfig(pageSize = 30),
            pagingSourceFactory = { GithubRepositoriesPagingSource(api)}
        ).flow
    }
}