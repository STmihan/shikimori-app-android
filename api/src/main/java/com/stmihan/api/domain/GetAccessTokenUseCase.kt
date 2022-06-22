package com.stmihan.api.domain

class GetAccessTokenUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): String {
        return "Bearer ${authRepository.getAccessToken()}"
    }
}