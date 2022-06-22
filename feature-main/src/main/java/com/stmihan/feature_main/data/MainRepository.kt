package com.stmihan.feature_main.data

import com.stmihan.core.domain.models.User

interface MainRepository {
    suspend fun getMe(): User?
}