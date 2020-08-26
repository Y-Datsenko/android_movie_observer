package com.datsenko.movieobserver.domain.auth

interface AuthRepository {

    suspend fun memberLogin(info: UserLoginInfo)
}
