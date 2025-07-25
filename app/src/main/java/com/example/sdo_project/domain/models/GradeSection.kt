package com.example.sdo_project.domain.models

data class GradeSection(
    val id: Int,
    val name: String,
    val maxScore: Int,
    val currentScore: Float? = null
)
