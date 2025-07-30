package com.example.sdo_project.presentation.reset

sealed class ResetEvent {
    data object Check: ResetEvent()
    data class ChangePassword( val password: String ): ResetEvent()
}