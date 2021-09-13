package com.joserrfborges.githubrepositories.presentation.ui.githubRepositories

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.joserrfborges.githubrepositories.databinding.ItemRepositoryBinding
import com.joserrfborges.githubrepositories.domain.model.Repository

class GithubRepositoriesViewHolder(private val binding: ItemRepositoryBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(repository: Repository) {
        binding.apply {
            Glide
                .with(binding.root)
                .load(repository.owner.avatar_url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.imageViewAvatar)

            textViewName.text = repository.name
            textViewAuthor.text = repository.owner.login
            textViewAmountFork.text = repository.forks.toString()
            textViewAmountStars.text = repository.stargazers_count.toString()
        }
    }
}