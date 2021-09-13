package com.joserrfborges.githubrepositories.presentation.ui.githubRepositories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.joserrfborges.githubrepositories.R
import com.joserrfborges.githubrepositories.databinding.FragmentRepositoriesBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class GithubRepositoriesFragment : Fragment() {
    private var _binding: FragmentRepositoriesBinding? = null
    private val binding get() = _binding
    private lateinit var githubRepositoriesAdapter: GithubRepositoriesAdapter
    private val viewModel by viewModel<GithubRepositoriesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepositoriesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMembers()
        observeViewModelRepositories()
        setActionOnClickButtonRetry()
    }

    private fun initMembers() {
        githubRepositoriesAdapter = GithubRepositoriesAdapter()
        binding?.apply {
            githubRepositoriesAdapter.run {
                recyclerViewRepositories.adapter = withLoadStateFooter(GithubRepositoriesLoadStateAdapter(this))
                addLoadStateListener { state ->
                    progressBarHome.isVisible = state.refresh is LoadState.Loading
                    buttonRetry.isVisible = state.refresh is LoadState.Error
                    textViewError.isVisible = state.refresh is LoadState.Error
                }
            }
        }
    }

    private fun observeViewModelRepositories() {
        lifecycleScope.launchWhenCreated {
            viewModel.githubRepositories.collectLatest {
                githubRepositoriesAdapter.submitData(it)
            }
        }
    }

    private fun setActionOnClickButtonRetry() {
        binding?.buttonRetry?.setOnClickListener {
            githubRepositoriesAdapter.refresh()
        }
    }
}

