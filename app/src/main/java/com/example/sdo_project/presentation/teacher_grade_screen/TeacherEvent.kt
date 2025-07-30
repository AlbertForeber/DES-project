package com.example.sdo_project.presentation.teacher_grade_screen

sealed class TeacherEvent {
    data class GetGroupsAndPoints(val teacherUuid: String, val disciplineId: Int): TeacherEvent()
    data class GetGrades(val groupId: Int, val pointId: Int): TeacherEvent()
}