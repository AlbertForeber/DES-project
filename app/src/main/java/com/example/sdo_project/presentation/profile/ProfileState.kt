package com.example.sdo_project.presentation.profile

import com.example.sdo_project.domain.models.User
import com.example.sdo_project.presentation.auth.AuthState

sealed class ProfileState {
    data class Idle( val email: String ): ProfileState()
    data object Loading: ProfileState()
    data class Error(val errorMessage: String, val email: String): ProfileState()
    data object SuccessEdit: ProfileState()
}