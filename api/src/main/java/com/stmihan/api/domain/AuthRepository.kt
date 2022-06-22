package com.stmihan.api.domain

interface AuthRepository {
    suspend fun getAccessToken(): String?
}