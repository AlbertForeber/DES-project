package com.example.sdo_project.presentation.auth

sealed class AuthEvent {
    data class Login(val email: String, val password: String): AuthEvent()
    data class Reset(val email: String): AuthEvent()
    data class ChangePassword(val password: String): AuthEvent()
    data class Recheck(val isReset: Boolean): AuthEvent()
    data object Logout: AuthEvent()

}
