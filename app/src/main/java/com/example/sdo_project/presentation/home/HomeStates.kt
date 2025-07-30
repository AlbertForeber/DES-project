package com.example.sdo_project.presentation.home

import com.example.sdo_project.domain.models.Discipline
import com.example.sdo_project.domain.models.User

sealed class HomeStates {
    object EmptyStart : HomeStates()
    object Loading: HomeStates()
    data class HomeSuccessState (val user: User, val disciplines: List<Discipline>): HomeStates()

}