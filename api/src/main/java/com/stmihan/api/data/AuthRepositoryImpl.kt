package com.stmihan.api.data

import android.content.SharedPreferences
import android.util.Log
import com.stmihan.api.api.gettokens.getaccesstokenviaauthсode.GetAccessTokenViaAuthCodeApi
import com.stmihan.api.api.gettokens.getaccesstokenviaauthсode.GetAccessTokenViaAuthCodeBody
import com.stmihan.api.api.gettokens.getaccesstokenviarefresh.GetAccessTokenViaRefreshApi
import com.stmihan.api.api.gettokens.getaccesstokenviarefresh.GetAccessTokenViaRefreshBody
import com.stmihan.api.domain.AuthRepository
import com.stmihan.core.Constants

class AuthRepositoryImpl(
    private val sharedPreferences: SharedPreferences,
    private val getAccessTokenViaAuthCodeApi: GetAccessTokenViaAuthCodeApi,
    private val getAccessTokenViaRefreshApi: GetAccessTokenViaRefreshApi
) : AuthRepository {
    override suspend fun getAccessToken(): String? {
        var token: String? = sharedPreferences.getString(Constants.SP_ACCESS_TOKEN, "")
        if (token.isNullOrBlank()) {
            token = getAccessTokenViaRefresh()
            if (token.isNullOrBlank()) {
                token = getAccessTokenViaAuthCode()
            }
        }
        return token
    }

    private suspend fun getAccessTokenViaAuthCode(): String? {
        val authCode = sharedPreferences.getString(Constants.SP_AUTH_KEY, "")
        if (authCode.isNullOrBlank()) return null

        try {
            val body = GetAccessTokenViaAuthCodeBody(
                grant_type = "authorization_code",
                client_id = Constants.CLIENT_ID,
                client_secret = Constants.CLIENT_SECRET,
                code = authCode,
                redirect_uri = "urn:ietf:wg:oauth:2.0:oob"
            )

            val response = getAccessTokenViaAuthCodeApi.getCode(body)

            response.let {
                if (response.body() == null) return null
                sharedPreferences.edit()
                    .putString(Constants.SP_ACCESS_TOKEN, response.body()?.access_token)
                    .putString(Constants.SP_REFRESH_TOKEN, response.body()?.refresh_token)
                    .apply()
                return response.body()?.access_token
            }
        } catch (e: Exception) {
            return null
        }
    }

    private suspend fun getAccessTokenViaRefresh(): String? {
        val refreshToken = getRefreshTokenFromSharedPrefs()
        if (refreshToken.isBlank()) {
            return null
        }

        try {
            val body = GetAccessTokenViaRefreshBody(
                grant_type = "refresh_token",
                client_id = Constants.CLIENT_ID,
                client_secret = Constants.CLIENT_SECRET,
                refresh_token = refreshToken
            )

            val codeResponse = getAccessTokenViaRefreshApi.getCode(body)

            codeResponse.let {
                sharedPreferences.edit()
                    .putString(Constants.SP_ACCESS_TOKEN, codeResponse.body()?.access_token)
                    .putString(Constants.SP_REFRESH_TOKEN, codeResponse.body()?.refresh_token)
                    .apply()
                return codeResponse.body()?.access_token
            }
        } catch (e: Exception) {
            return null
        }
    }

    private fun getRefreshTokenFromSharedPrefs(): String {
        return sharedPreferences.getString(Constants.SP_REFRESH_TOKEN, "") ?: ""
    }
}