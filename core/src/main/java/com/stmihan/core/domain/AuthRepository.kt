package com.stmihan.core.domain

interface AuthRepository {
    fun getAccessToken(): String?
}