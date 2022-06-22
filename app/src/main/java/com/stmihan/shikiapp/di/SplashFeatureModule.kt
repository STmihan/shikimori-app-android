package com.stmihan.shikiapp.di

import com.stmihan.api.api.whoami.WhoAmIApi
import com.stmihan.shikiapp.data.repositories.SplashRepositoryImpl
import com.stmihan.shikiapp.domain.SplashRepository
import com.stmihan.shikiapp.presentation.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val splashFeatureModule = module {
    factory {
        provideWhoAmIApi(
            retrofit =
            get()
        )
    }
    factory<SplashRepository> {
        SplashRepositoryImpl(
            whoAmIApi = get(),
            getAccessTokenUseCase = get()
        )
    }
    viewModel {
        SplashViewModel(
            splashRepository = get()
        )
    }
}

fun provideWhoAmIApi(retrofit: Retrofit): WhoAmIApi {
    return retrofit.create(WhoAmIApi::class.java)
}