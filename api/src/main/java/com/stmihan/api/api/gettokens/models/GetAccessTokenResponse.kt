package com.stmihan.api.api.gettokens.models

data class GetAccessTokenResponse(
    val access_token: String,
    val token_type: String,
    val expires_in: Long,
    val refresh_token: String,
    val scope: String,
    val created_at: Long
)