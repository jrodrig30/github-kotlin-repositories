package com.joserrfborges.githubrepositories.presentation.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.joserrfborges.githubrepositories.presentation.ui.githubRepositories.GithubRepositoriesActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        goToContainerActivity()
    }

    private fun goToContainerActivity() {
        startActivity(Intent(this, GithubRepositoriesActivity::class.java))
        finish()

    }
}