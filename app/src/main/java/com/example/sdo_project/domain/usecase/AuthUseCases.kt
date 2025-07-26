package com.example.sdo_project.domain.usecase

data class AuthUseCases(
    val login: LoginUseCase,
    val register: SignupUseCase,
    val reset: ResetUseCase,
    val logout: LogoutUseCase,
    val getToken: GetTokenUseCase,
    val changePass: ChangePasswordUseCase
)