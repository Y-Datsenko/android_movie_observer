package com.datsenko.movieobserver.presentation.login

sealed class LoginViewState {
    object Initial : LoginViewState()

    data class LoginInfo(
        val model: LoginPresenter.LoginInfoModel
    ) : LoginViewState()
}
