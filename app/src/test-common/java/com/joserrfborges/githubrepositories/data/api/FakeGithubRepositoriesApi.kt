package com.joserrfborges.githubrepositories.data.api

import com.joserrfborges.githubrepositories.domain.model.GithubApiResponse
import com.joserrfborges.githubrepositories.domain.model.Repository
import java.io.IOException
import kotlin.math.min

class FakeGithubRepositoriesApi : GithubRepositoriesApi {
    private val model =  mutableListOf<Repository>()
    var failureMsg: String? = null

    fun addRepository(repository: Repository) {
        model.add(repository)
    }

    fun getRepositories(limit: Int): MutableList<Repository> {
        return model.subList(0, min(model.size, limit))
    }

    override suspend fun getGithubRepositories(page: Int, itemsPerPage: Int): GithubApiResponse {
        failureMsg?.let {
            throw IOException(it)
        }

        return GithubApiResponse(
            total = model.size,
            items = getRepositories(itemsPerPage)
        )
    }
}