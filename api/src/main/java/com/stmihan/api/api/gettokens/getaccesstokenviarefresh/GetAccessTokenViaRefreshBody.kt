package com.stmihan.api.api.gettokens.getaccesstokenviarefresh

data class GetAccessTokenViaRefreshBody(
    val grant_type: String,
    val client_id: String,
    val client_secret: String,
    val refresh_token: String
)