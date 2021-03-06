package com.joserrfborges.githubrepositories.domain.model

import com.google.gson.annotations.SerializedName


data class GithubApiResponse(
    @SerializedName("total_count") val total: Int = 0,
    @SerializedName("items") val items: List<Repository> = emptyList(),
    val nextPage: Int? = null
)
