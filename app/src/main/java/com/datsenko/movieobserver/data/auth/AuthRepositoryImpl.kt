package com.datsenko.movieobserver.data.auth

import com.datsenko.movieobserver.data.auth.model.request.MemberSessionRequest
import com.datsenko.movieobserver.data.auth.model.request.MemberTokenValidateRequest
import com.datsenko.movieobserver.domain.auth.AuthRepository
import com.datsenko.movieobserver.domain.auth.UserLoginInfo
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService,
    private val authEncryptedStorage: AuthEncryptedStorageApi
) : AuthRepository {

    override suspend fun memberLogin(info: UserLoginInfo) {
        val requestToken = authService.getRequestToken()
        val validatedToken = authService.validateRequestTokenWithLogin(
            MemberTokenValidateRequest(
                username = info.username,
                password = info.password,
                requestToken = requestToken.requestToken
            )
        )
        val session = authService.getMemberSession(MemberSessionRequest(validatedToken.requestToken))
        authEncryptedStorage.saveMemberSessionId(session.sessionId)
        authEncryptedStorage.updateUserIsGuest(isGuest = false)
    }
}
