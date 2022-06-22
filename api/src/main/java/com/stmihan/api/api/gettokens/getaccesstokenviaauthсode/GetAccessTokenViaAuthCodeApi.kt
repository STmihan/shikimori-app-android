package com.stmihan.api.api.gettokens.getaccesstokenviaauthсode

import com.stmihan.api.api.gettokens.models.GetAccessTokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface GetAccessTokenViaAuthCodeApi {

    @POST("/oauth/token")
    suspend fun getCode(@Body body: GetAccessTokenViaAuthCodeBody): Response<GetAccessTokenResponse>
}

