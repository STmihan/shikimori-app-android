package com.stmihan.shikiapp.interceptors

import com.stmihan.core.domain.GetAccessTokenUseCase
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val getAccessTokenUseCase: GetAccessTokenUseCase
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val accessToken = getAccessTokenUseCase.invoke()
            ?: return chain.proceed(request)

        return chain.proceed(
            request
                .newBuilder()
                .addHeader("Authorization", "Bearer $accessToken")
                .build()
        )
    }
}