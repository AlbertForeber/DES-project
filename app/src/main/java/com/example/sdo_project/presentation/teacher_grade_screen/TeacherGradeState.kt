package com.example.sdo_project.presentation.teacher_grade_screen

import com.example.sdo_project.domain.models.Group

sealed class TeacherGradeState {
    data class Idle(val availableGroups: List<Group>): TeacherGradeState()
    data object Loading: TeacherGradeState()
    data class Error(val errorMessage: String): TeacherGradeState()
}