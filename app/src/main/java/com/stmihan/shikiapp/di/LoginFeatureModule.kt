package com.stmihan.shikiapp.di

import com.stmihan.feature_login.data.LoginRepositoryImpl
import com.stmihan.feature_login.domain.LoginRepository
import com.stmihan.feature_login.domain.SaveAuthKeyUseCase
import com.stmihan.feature_login.presentation.WebLoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginFeatureModule = module {
    factory<LoginRepository> { LoginRepositoryImpl(
        sharedPreferences = get()
    ) }
    factory { SaveAuthKeyUseCase(
        loginRepository = get()
    ) }
    viewModel { WebLoginViewModel(
        saveAuthKeyUseCase = get()
    ) }
}