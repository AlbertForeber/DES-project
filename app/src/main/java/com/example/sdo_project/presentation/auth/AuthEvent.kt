package com.example.sdo_project.presentation.auth

sealed class AuthEvent {
    data class Login(val email: String, val password: String): AuthEvent()
    data class Reset(val email: String): AuthEvent()
}
