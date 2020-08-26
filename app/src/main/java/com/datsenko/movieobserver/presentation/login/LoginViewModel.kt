package com.datsenko.movieobserver.presentation.login

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import com.datsenko.movieobserver.presentation.events.GoBack

class LoginViewModel @ViewModelInject constructor(
    private val presenter: LoginPresenter,
    @Assisted private val stateHandle: SavedStateHandle
) : RainbowCakeViewModel<LoginViewState>(LoginViewState.Initial) {

    private val username: String
        get() = stateHandle.get<String>(ARG_USERNAME).orEmpty()
    private val password: String
        get() = stateHandle.get<String>(ARG_USER_PASSWORD).orEmpty()

    fun onViewStarted() {
        if (viewState == LoginViewState.Initial) {
            viewState =
                LoginViewState.LoginInfo(LoginPresenter.LoginInfoModel(username = username, password = password))
        }
    }

    fun onBackButtonClicked() {
        postEvent(GoBack)
    }

    fun onUsernameChanged(username: String) {
        stateHandle.set(ARG_USERNAME, username)
    }

    fun onPasswordChanged(password: String) {
        stateHandle.set(ARG_USER_PASSWORD, password)
    }

    fun onLoginClicked() = execute {
        presenter.memberLogin(LoginPresenter.LoginInfoModel(username, password))
    }

    companion object {

        const val ARG_USERNAME = "USERNAM"
        const val ARG_USER_PASSWORD = "PASSWORD"
    }
}
