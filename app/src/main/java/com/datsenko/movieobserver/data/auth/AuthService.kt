package com.datsenko.movieobserver.data.auth

import com.datsenko.movieobserver.data.auth.model.MemberSessionResponse
import com.datsenko.movieobserver.data.auth.model.RequestTokenResponse
import com.datsenko.movieobserver.data.auth.model.request.MemberSessionRequest
import com.datsenko.movieobserver.data.auth.model.request.MemberTokenValidateRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthService {

    @GET("authentication/token/new")
    suspend fun getRequestToken(): RequestTokenResponse

    @POST("authentication/token/validate_with_login")
    suspend fun validateRequestTokenWithLogin(
        @Body body: MemberTokenValidateRequest
    ): RequestTokenResponse

    @GET("authentication/session/new")
    suspend fun getMemberSession(
        @Body body: MemberSessionRequest
    ): MemberSessionResponse
}
