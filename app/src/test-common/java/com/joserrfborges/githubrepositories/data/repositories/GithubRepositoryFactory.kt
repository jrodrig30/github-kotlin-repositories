package com.joserrfborges.githubrepositories.data.repositories

import com.joserrfborges.githubrepositories.domain.model.Owner
import com.joserrfborges.githubrepositories.domain.model.Repository
import java.util.concurrent.atomic.AtomicInteger

class GithubRepositoryFactory {
    private val counter = AtomicInteger(0)
    fun createGithubRepository() : Repository {
        val id = counter.incrementAndGet()
        val owner = Owner(
            login = "author_$id",
            avatar_url = "https://avatar.url.$id"
        )

        return Repository(
            name = "$id",
            owner = owner,
            stargazers_count = id,
            forks = id
        )
    }
}