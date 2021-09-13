package com.joserrfborges.githubrepositories.presentation.ui.githubRepositories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.joserrfborges.githubrepositories.R
import android.view.WindowManager
import android.graphics.drawable.Drawable

class GithubRepositoriesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repositories)
        val background: Drawable = resources.getDrawable(R.drawable.background_gradient, null)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(android.R.color.transparent, null)
        window.navigationBarColor = (resources.getColor(android.R.color.transparent, null))
        window.setBackgroundDrawable(background)
    }
}