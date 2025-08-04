package com.example.sdo_project.presentation.teacher_grade_screen.components.change_grade

sealed class ChangeGradeEvent {
    data class ChangeGrade(val newGrade: Float, val pointId: Int, val uuid: String): ChangeGradeEvent()
    data object BackToIdle: ChangeGradeEvent()
}