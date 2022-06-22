package com.stmihan.core.domain.models

data class User(
    val avatar: String?,
    val birthOn: String?, //TODO: To Date
    val id: Long,
    val image: Image?,
    val lastOnlineAt: String?, //TODO: To Date
    val locale: String?,
    val name: String?,
    val nickname: String?,
    val sex: String?,
    val website: String?
)