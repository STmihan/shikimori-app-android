package com.stmihan.shikiapp.data.repositories

import android.util.Log
import com.stmihan.api.api.whoami.WhoAmIApi
import com.stmihan.api.domain.GetAccessTokenUseCase
import com.stmihan.shikiapp.domain.SplashRepository

class SplashRepositoryImpl(
    private val whoAmIApi: WhoAmIApi,
    private val getAccessTokenUseCase: GetAccessTokenUseCase
) : SplashRepository {
    override suspend fun tryLogin(): Boolean {
        try {
            val token = getAccessTokenUseCase.invoke()
            val response = whoAmIApi.getMe(token)
            response?.let { return if (response.isSuccessful) response.body() != null else false }
            return false
        } catch (e: Exception) {
            return false
        }
    }
}