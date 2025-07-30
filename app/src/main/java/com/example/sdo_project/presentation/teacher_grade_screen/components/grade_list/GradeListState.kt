package com.example.sdo_project.presentation.teacher_grade_screen.components.grade_list

import com.example.sdo_project.domain.models.GradeWithStudentInfo
import com.example.sdo_project.domain.models.Group
import com.example.sdo_project.domain.models.User
import com.example.sdo_project.presentation.auth.AuthState

sealed class GradeListState {
    data class Idle(val grades: List<GradeWithStudentInfo>): GradeListState()
    data object Loading: GradeListState()
    data class Error(val errorMessage: String): GradeListState()
}