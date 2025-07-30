package com.example.sdo_project.domain.usecase.auth

data class AuthUseCases(
    val login: LoginUseCase,
    val register: SignupUseCase,
    val reset: ResetUseCase,
)