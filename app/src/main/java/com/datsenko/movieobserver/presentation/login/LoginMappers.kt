package com.datsenko.movieobserver.presentation.login

import com.datsenko.movieobserver.domain.auth.UserLoginInfo

fun LoginPresenter.LoginInfoModel.toDomain(): UserLoginInfo =
    UserLoginInfo(
        username = username,
        password = password
    )
