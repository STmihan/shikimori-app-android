package com.stmihan.feature_login.domain

class SaveAuthKeyUseCase(
    private val loginRepository: LoginRepository
) {
    operator fun invoke(key: String) {
        loginRepository.saveKey(key)
    }
}