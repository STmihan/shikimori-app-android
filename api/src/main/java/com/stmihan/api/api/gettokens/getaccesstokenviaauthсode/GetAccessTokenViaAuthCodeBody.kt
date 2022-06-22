package com.stmihan.api.api.gettokens.getaccesstokenviaauthсode

data class GetAccessTokenViaAuthCodeBody(
    val grant_type: String,
    val client_id: String,
    val client_secret: String,
    val code: String,
    val redirect_uri: String
)

