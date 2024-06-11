package com.example.projectf

import android.app.Application
//import com.example.projectf.data.di.dataModule
//import com.example.projectf.domain.di.domainModule
import com.example.projectf.presentation.di.presentationModule
import dagger.hilt.android.HiltAndroidApp
import org.koin.core.context.startKoin
@HiltAndroidApp
class App:Application() {

    /*override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(
                dataModule, domainModule, presentationModule
            ))
        }
    }*/
}