package com.example.sdo_project.presentation.reset

import com.example.sdo_project.domain.models.User
import com.example.sdo_project.presentation.auth.AuthState

sealed class ResetState {
    data object Allowed: ResetState()
    data object Loading: ResetState()
    data object Success: ResetState()
    data class Error(val errorMessage: String): ResetState()
    data object WrongLink: ResetState()
}