package com.stmihan.feature_main.repository

import android.util.Log
import com.stmihan.api.api.whoami.WhoAmIApi
import com.stmihan.api.domain.GetAccessTokenUseCase
import com.stmihan.core.domain.models.User
import com.stmihan.feature_main.data.MainRepository

class MainRepositoryImpl(
    private val whoAmIApi: WhoAmIApi,
    private val getAccessTokenUseCase: GetAccessTokenUseCase
) : MainRepository {
    override suspend fun getMe(): User? {
        val token = getAccessTokenUseCase.invoke()
        val response = whoAmIApi.getMe(token)

        response?.let {
            if (response.isSuccessful) {
                return response.body()?.toUser()
            } else {
                return null
            }
        }
        return null
    }
}