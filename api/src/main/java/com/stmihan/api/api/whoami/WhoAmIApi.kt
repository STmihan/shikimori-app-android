package com.stmihan.api.api.whoami

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface WhoAmIApi {

    @GET("/api/users/whoami")
    suspend fun getMe(@Header("Authorization") token: String): Response<WhoAmIResponse?>?
}