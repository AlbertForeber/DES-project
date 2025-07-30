package com.example.sdo_project.domain.usecase.auth

import com.example.sdo_project.domain.usecase.user.GetTokenUseCase

data class AuthUseCases(
    val login: LoginUseCase,
    val register: SignupUseCase,
    val reset: ResetUseCase,
    val logout: LogoutUseCase,
    val getToken: GetTokenUseCase
)