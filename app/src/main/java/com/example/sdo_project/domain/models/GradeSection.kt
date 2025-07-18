package com.example.sdo_project.domain.models

data class GradeSection(
    val id: Int,
    val name: String,
    val max_grade: Float,
    val current_grade: Float? = null
)
