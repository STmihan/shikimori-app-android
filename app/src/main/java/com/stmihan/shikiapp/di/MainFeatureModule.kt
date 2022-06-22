package com.stmihan.shikiapp.di

import com.stmihan.api.api.whoami.WhoAmIApi
import com.stmihan.feature_main.MainViewModel
import com.stmihan.feature_main.data.MainRepository
import com.stmihan.feature_main.repository.MainRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val mainFeatureModule = module {
    factory {
        provideRetrofit(
            retrofit = get()
        )
    }
    factory<MainRepository> { MainRepositoryImpl(
        whoAmIApi = get(),
        getAccessTokenUseCase = get()
    ) }

    viewModel { MainViewModel(
        mainRepository = get()
    ) }
}

fun provideRetrofit(retrofit: Retrofit): WhoAmIApi {
    return retrofit.create(WhoAmIApi::class.java)
}