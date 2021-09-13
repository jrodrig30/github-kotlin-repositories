package com.joserrfborges.githubrepositories.data.api

import com.joserrfborges.githubrepositories.domain.model.GithubApiResponse
import retrofit2.http.Query
import retrofit2.http.GET

interface GithubRepositoriesApi {
    @GET("search/repositories?q=language:kotlin&sort=stars")
    suspend fun getGithubRepositories(
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int
    ): GithubApiResponse

}