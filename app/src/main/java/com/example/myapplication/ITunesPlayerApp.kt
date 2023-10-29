package com.example.myapplication

import android.app.Application
import com.example.myapplication.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ITunesPlayerApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        // start Koin!
        startKoin {

            // Android context
            androidContext(this@ITunesPlayerApp)

            // modules
            modules(appComponent)
        }
    }
}
