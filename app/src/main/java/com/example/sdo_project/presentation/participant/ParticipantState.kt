package com.example.sdo_project.presentation.participant

import com.example.sdo_project.domain.models.Group

sealed class ParticipantState{
    object Start: ParticipantState()
    object Loading: ParticipantState()
    data class Success (val groups: List<Group>) : ParticipantState()
}
