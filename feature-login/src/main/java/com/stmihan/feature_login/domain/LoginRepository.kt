package com.stmihan.feature_login.domain

interface LoginRepository {
    fun saveKey(key: String)
}