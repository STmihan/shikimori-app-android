package com.stmihan.core.domain

class GetAccessTokenUseCase(
    private val authRepository: AuthRepository
) {
    operator fun invoke(): String? {
        return authRepository.getAccessToken()
    }
}