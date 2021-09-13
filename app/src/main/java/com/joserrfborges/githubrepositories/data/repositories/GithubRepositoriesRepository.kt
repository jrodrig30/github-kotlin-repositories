package com.joserrfborges.githubrepositories.data.repositories

import androidx.paging.PagingData
import com.joserrfborges.githubrepositories.domain.model.Repository
import kotlinx.coroutines.flow.Flow

interface GithubRepositoriesRepository {
     fun getGithubRepositories() : Flow<PagingData<Repository>>
}