package com.stmihan.core.data

import android.content.SharedPreferences
import com.stmihan.core.Constants
import com.stmihan.core.domain.AuthRepository

class AuthRepositoryImpl(
    private val sharedPreferences: SharedPreferences
) : AuthRepository {
    override fun getAccessToken(): String? {
        return sharedPreferences.getString(Constants.SP_ACCESS_TOKEN, null)
    }
}