package com.stmihan.shikiapp.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.stmihan.core.Constants
import org.koin.dsl.module

val sharedPreferencesModule = module {
    single { defineSharedPreferences(
        application = get()
    ) }
}

fun defineSharedPreferences(application: Application): SharedPreferences {
    return application.getSharedPreferences(Constants.SP_APP_KEY, Context.MODE_PRIVATE)
}