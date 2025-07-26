package com.example.sdo_project.presentation.auth

import com.example.sdo_project.domain.models.User

sealed class AuthState {
    data object Idle: AuthState()
    data object Loading: AuthState()
    data class Authorized(val uuid: String): AuthState()
    data class Error(val errorMessage: String): AuthState()
}