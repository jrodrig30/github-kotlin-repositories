package com.joserrfborges.githubrepositories.domain.usecases

import androidx.paging.PagingData
import com.joserrfborges.githubrepositories.data.repositories.GithubRepositoriesRepository
import com.joserrfborges.githubrepositories.domain.model.Repository
import kotlinx.coroutines.flow.Flow

class GithubRepositoriesUseCase (private val githubRepositoriesRepository: GithubRepositoriesRepository){
     fun execute() : Flow<PagingData<Repository>> {
         return githubRepositoriesRepository.getGithubRepositories()
     }
}