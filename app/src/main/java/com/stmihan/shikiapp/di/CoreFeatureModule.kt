package com.stmihan.shikiapp.di

import com.stmihan.core.data.AuthRepositoryImpl
import com.stmihan.core.domain.AuthRepository
import com.stmihan.core.domain.GetAccessTokenUseCase
import org.koin.dsl.module

val coreFeatureModule = module {
    factory<AuthRepository> {
        AuthRepositoryImpl(
            sharedPreferences = get()
        )
    }
    factory {
        GetAccessTokenUseCase(
            authRepository = get()
        )
    }
}