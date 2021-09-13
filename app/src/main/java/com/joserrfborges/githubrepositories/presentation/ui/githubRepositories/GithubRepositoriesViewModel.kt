package com.joserrfborges.githubrepositories.presentation.ui.githubRepositories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.joserrfborges.githubrepositories.domain.model.Repository
import com.joserrfborges.githubrepositories.domain.usecases.GithubRepositoriesUseCase
import kotlinx.coroutines.flow.Flow

class GithubRepositoriesViewModel(
    githubRepositoriesUseCase: GithubRepositoriesUseCase
) : ViewModel() {
    val githubRepositories: Flow<PagingData<Repository>> =
        githubRepositoriesUseCase.execute().cachedIn(viewModelScope)
}