package com.example.sdo_project.presentation.student_grade

import com.example.sdo_project.domain.models.GradeSection

sealed class StudentGradeState {
    object Start: StudentGradeState()
    object Loading: StudentGradeState()
    data class Success (val sectionGradeList: List<GradeSection>): StudentGradeState()
    data class Error (val message: String): StudentGradeState()
}