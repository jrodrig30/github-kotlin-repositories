package com.joserrfborges.githubrepositories.data.repositories

import androidx.paging.PagingSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import kotlin.test.assertEquals
import androidx.paging.PagingSource.LoadResult.Page
import androidx.paging.PagingSource.LoadParams.Refresh
import com.joserrfborges.githubrepositories.data.api.FakeGithubRepositoriesApi
import com.joserrfborges.githubrepositories.domain.model.Repository

@OptIn(ExperimentalCoroutinesApi::class)
class GithubRepositoriesPagingSourceTest {
    private val repositoryFactory = GithubRepositoryFactory()
    private val fakeRepositories = listOf(
        repositoryFactory.createGithubRepository(),
        repositoryFactory.createGithubRepository(),
        repositoryFactory.createGithubRepository()
    )

    private val fakeApi = FakeGithubRepositoriesApi().apply {
        fakeRepositories.forEach { repository -> addRepository(repository) }
    }

    @Test
    fun githubRepositoriesPagingSourceTest() = runBlockingTest {
        val pagingSource = GithubRepositoriesPagingSource(fakeApi)
        val actual: PagingSource.LoadResult<Int, Repository> = pagingSource.load(
            Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        val expected: PagingSource.LoadResult<Int, Repository> = Page(
            data = listOf(fakeRepositories[0], fakeRepositories[1]),
            prevKey = null,
            nextKey = fakeRepositories[1].name.toInt()
        )

        assertEquals(expected, actual)
    }
}