package com.joserrfborges.githubrepositories.data.repositories

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.joserrfborges.githubrepositories.data.api.GithubRepositoriesApi
import com.joserrfborges.githubrepositories.domain.model.Repository
import java.io.IOException
import retrofit2.HttpException
import androidx.paging.PagingSource.LoadResult.Page

private const val GITHUB_STARTING_PAGE_INDEX = 1

class GithubRepositoriesPagingSource(
    private val api: GithubRepositoriesApi,
) : PagingSource<Int, Repository>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repository> {
        val position = params.key ?: GITHUB_STARTING_PAGE_INDEX
        return try {
            val response = api.getGithubRepositories(position, params.loadSize)
            val nextKey = position + 1

            Page(
                data = response.items,
                prevKey = if (position == GITHUB_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (response.items.isEmpty()) null else nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Repository>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}