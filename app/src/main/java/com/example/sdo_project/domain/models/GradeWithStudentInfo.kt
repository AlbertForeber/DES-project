package com.example.sdo_project.domain.models

import kotlinx.serialization.SerialName

data class GradeWithStudentInfo(
    val name: String,
    val surname: String,
    val patronymic: String,
    val currentScore: Float,

    )
