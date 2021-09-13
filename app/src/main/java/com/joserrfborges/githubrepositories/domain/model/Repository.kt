package com.joserrfborges.githubrepositories.domain.model

data class Repository (
    val name: String,
    val owner: Owner,
    val stargazers_count: Int,
    val forks: Int
)

data class Owner(
    val login: String,
    val avatar_url: String
)