package com.example.sdo_project.presentation.student_grade

import com.example.sdo_project.domain.models.GradePoint

sealed class StudentPointGradeState {
    abstract val pointGrades: Map<Int, List<GradePoint>?>

    data class  Start (override val pointGrades: Map<Int, List<GradePoint>?>): StudentPointGradeState()
    data class  Loading (override val pointGrades: Map<Int, List<GradePoint>?>): StudentPointGradeState()
    data class  Success (override val pointGrades: Map<Int, List<GradePoint>?>): StudentPointGradeState()
    data class  Error (
            override val pointGrades: Map<Int, List<GradePoint>?>,
            val message: String
        ): StudentPointGradeState()

}