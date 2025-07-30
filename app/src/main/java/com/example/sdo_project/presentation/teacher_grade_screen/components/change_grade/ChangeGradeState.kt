package com.example.sdo_project.presentation.teacher_grade_screen.components.change_grade

import com.example.sdo_project.domain.models.GradeWithStudentInfo
import com.example.sdo_project.domain.models.Group
import com.example.sdo_project.domain.models.User
import com.example.sdo_project.presentation.auth.AuthState

sealed class ChangeGradeState {
    data object Success: ChangeGradeState()
    data object Loading: ChangeGradeState()
    data object Idle: ChangeGradeState()
    data class Error(val errorMessage: String): ChangeGradeState()
}