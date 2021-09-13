package com.joserrfborges.githubrepositories.presentation.ui.githubRepositories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.joserrfborges.githubrepositories.databinding.ItemRepositoryBinding
import com.joserrfborges.githubrepositories.domain.model.Repository

class GithubRepositoriesAdapter :
    PagingDataAdapter<Repository, GithubRepositoriesViewHolder>(REPOSITORIES_COMPARATOR) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GithubRepositoriesViewHolder {
        return GithubRepositoriesViewHolder(
            ItemRepositoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GithubRepositoriesViewHolder, position: Int) {
        getItem(position)?.let { gitHubRepository ->
            holder.bind(gitHubRepository)
        }
    }

    companion object {
        private val REPOSITORIES_COMPARATOR = object : DiffUtil.ItemCallback<Repository>() {
            override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean =
                oldItem == newItem
        }
    }

}