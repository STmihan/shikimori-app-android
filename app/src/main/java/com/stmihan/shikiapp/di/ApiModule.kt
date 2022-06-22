package com.stmihan.shikiapp.di

import com.stmihan.api.api.gettokens.getaccesstokenviaauthсode.GetAccessTokenViaAuthCodeApi
import com.stmihan.api.api.gettokens.getaccesstokenviarefresh.GetAccessTokenViaRefreshApi
import com.stmihan.api.data.AuthRepositoryImpl
import com.stmihan.api.domain.AuthRepository
import com.stmihan.api.domain.GetAccessTokenUseCase
import com.stmihan.api.interceptors.UserAgentInterceptor
import com.stmihan.core.Constants
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

val apiModule = module {

    factory { UserAgentInterceptor() }

    factory {
        provideOkHTTPClient(
            userAgentInterceptor = get()
        )
    }
    single {
        provideRetrofit(
            client = get()
        )
    }


    factory<AuthRepository> {
        AuthRepositoryImpl(
            sharedPreferences = get(),
            getAccessTokenViaAuthCodeApi = provideGetAccessTokenViaAuthCode(
                retrofit = get()
            ),
            getAccessTokenViaRefreshApi = provideGetAccessTokenViaRefresh(
                retrofit = get()
            )
        )
    }
    factory {
        GetAccessTokenUseCase(
            authRepository = get()
        )
    }

}

fun provideOkHTTPClient(userAgentInterceptor: UserAgentInterceptor): OkHttpClient {
    return OkHttpClient()
        .newBuilder()
        .addInterceptor(userAgentInterceptor)
        .build()
}

fun provideRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit
        .Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
}

fun provideGetAccessTokenViaAuthCode(retrofit: Retrofit): GetAccessTokenViaAuthCodeApi {
    return retrofit.create(GetAccessTokenViaAuthCodeApi::class.java)
}

fun provideGetAccessTokenViaRefresh(retrofit: Retrofit): GetAccessTokenViaRefreshApi {
    return retrofit.create(GetAccessTokenViaRefreshApi::class.java)
}