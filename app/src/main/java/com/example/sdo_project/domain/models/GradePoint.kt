package com.example.sdo_project.domain.models

import kotlinx.serialization.SerialName

data class GradePoint(
    val id: Int,
    val name: String,
    val currentScore: Int,
    val maxScore: Int
)
