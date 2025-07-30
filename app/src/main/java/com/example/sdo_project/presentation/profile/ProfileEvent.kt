package com.example.sdo_project.presentation.profile

import com.example.sdo_project.domain.models.User

sealed class ProfileEvent {
    data class EditUser( val user: User ): ProfileEvent()
}