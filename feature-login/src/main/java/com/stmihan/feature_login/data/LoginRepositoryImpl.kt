package com.stmihan.feature_login.data

import android.content.SharedPreferences
import com.stmihan.core.Constants
import com.stmihan.feature_login.domain.LoginRepository

class LoginRepositoryImpl(
    private val sharedPreferences: SharedPreferences
) : LoginRepository {
    override fun saveKey(key: String) {
        sharedPreferences
            .edit()
            .putString(Constants.SP_AUTH_KEY, key)
            .apply()
    }

}