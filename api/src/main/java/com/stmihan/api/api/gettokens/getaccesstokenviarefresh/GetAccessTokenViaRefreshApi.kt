package com.stmihan.api.api.gettokens.getaccesstokenviarefresh

import com.stmihan.api.api.gettokens.models.GetAccessTokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface GetAccessTokenViaRefreshApi {

    @POST("/oauth/token")
    suspend fun getCode(@Body body: GetAccessTokenViaRefreshBody): Response<GetAccessTokenResponse>
}

