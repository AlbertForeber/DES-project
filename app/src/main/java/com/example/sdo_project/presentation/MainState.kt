package com.example.sdo_project.presentation

import com.example.sdo_project.domain.models.User
import com.example.sdo_project.presentation.auth.AuthState

sealed class MainState {
    data object Unauthorized: MainState()
    data class Authorized(val user: User): MainState()
}