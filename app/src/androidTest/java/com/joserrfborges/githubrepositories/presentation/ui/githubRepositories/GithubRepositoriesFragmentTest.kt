package com.joserrfborges.githubrepositories.presentation.ui.githubRepositories

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.paging.Pager
import androidx.paging.PagingConfig

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.joserrfborges.githubrepositories.R
import com.joserrfborges.githubrepositories.data.api.FakeGithubRepositoriesApi
import com.joserrfborges.githubrepositories.data.repositories.GithubRepositoriesPagingSource
import com.joserrfborges.githubrepositories.data.repositories.GithubRepositoryFactory
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.*
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class GithubRepositoriesFragmentTest : AutoCloseKoinTest() {
    private val repositoryFactory = GithubRepositoryFactory()
    private val fakeRepositories = listOf(
        repositoryFactory.createGithubRepository(),

    )

    private val fakeApi = FakeGithubRepositoriesApi().apply {
        fakeRepositories.forEach { repository -> addRepository(repository) }
    }


    private lateinit var scenario: FragmentScenario<GithubRepositoriesFragment>

    private val githubRepositoriesViewModel: GithubRepositoriesViewModel = mockk(relaxed = true)


    @Before
    fun setUp() {
        try {
            stopKoin()
        } catch (exception: Exception) {

        }

        startKoin {
            modules(
                module { viewModel { githubRepositoriesViewModel } }
            )
        }


        val repositories = Pager(
            config = PagingConfig(pageSize = 1),
            pagingSourceFactory = { GithubRepositoriesPagingSource(fakeApi) }
        ).flow

       every { githubRepositoriesViewModel.githubRepositories } returns repositories

        scenario = launchFragmentInContainer(themeResId = R.style.Theme_GithubRepositories)
        scenario.moveToState(newState = Lifecycle.State.STARTED)
    }

    @After
    fun tearDown() {
        stopKoin()
    }


    @Test
    fun test_recycler_view_repositories_is_visible_on_app_launch() {
        Espresso.onView(ViewMatchers.withId(R.id.recycler_view_repositories)).check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }

    @Test
    fun test_error_components_are_hidden_app_launch() {
        Espresso.onView(ViewMatchers.withId(R.id.button_retry)).check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        Espresso.onView(ViewMatchers.withId(R.id.text_view_error)).check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
    }

}