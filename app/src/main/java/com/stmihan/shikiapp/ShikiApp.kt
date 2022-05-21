package com.stmihan.shikiapp

import android.app.Application
import com.stmihan.shikiapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ShikiApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@ShikiApp)
            modules(appModule)
        }
    }
}