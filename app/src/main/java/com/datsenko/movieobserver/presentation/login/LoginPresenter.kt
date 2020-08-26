package com.datsenko.movieobserver.presentation.login

import com.datsenko.movieobserver.domain.auth.AuthRepository
import javax.inject.Inject

class LoginPresenter @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend fun memberLogin(info: LoginInfoModel) =
        authRepository.memberLogin(info.toDomain())

    data class LoginInfoModel(
        val username: String = "",
        val password: String = ""
    )
}
