package com.stmihan.shikiapp.domain

interface SplashRepository {
    suspend fun tryLogin(): Boolean
}