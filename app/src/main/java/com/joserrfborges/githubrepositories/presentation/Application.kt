package com.joserrfborges.githubrepositories.presentation

import android.app.Application
import com.joserrfborges.githubrepositories.data.di.dataModule
import com.joserrfborges.githubrepositories.domain.di.domainModule
import com.joserrfborges.githubrepositories.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Application)
            modules(
                domainModule,
                dataModule,
                presentationModule
            )
        }
    }
}