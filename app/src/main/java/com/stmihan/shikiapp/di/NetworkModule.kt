package com.stmihan.shikiapp.di

import com.stmihan.core.Constants
import com.stmihan.shikiapp.interceptors.AuthInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {
    factory {
        AuthInterceptor(
            getAccessTokenUseCase = get()
        )
    }
    factory {
        provideOkHTTPClient(
            authInterceptor = get()
        )
    }
    single {
        provideRetrofitWithoutLogin(
            client = get()
        )
    }
}

fun provideOkHTTPClient(authInterceptor: AuthInterceptor): OkHttpClient {
    return OkHttpClient()
        .newBuilder()
        .addInterceptor(authInterceptor)
        .build()
}

fun provideRetrofitWithoutLogin(client: OkHttpClient): Retrofit {
    return Retrofit
        .Builder()
        .baseUrl(Constants.BASE_URL)
        .client(client)
        .build()
}