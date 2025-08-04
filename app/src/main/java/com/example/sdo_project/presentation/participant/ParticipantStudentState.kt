package com.example.sdo_project.presentation.participant

import com.example.sdo_project.domain.models.User

sealed class ParticipantStudentListState {
    object Start: ParticipantStudentListState()
    object Loading: ParticipantStudentListState()
    data class Success (val participants: List<User>): ParticipantStudentListState()
    data class Error (val message: String): ParticipantStudentListState()
}