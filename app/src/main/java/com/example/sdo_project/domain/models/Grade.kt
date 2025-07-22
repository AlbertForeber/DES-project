package com.example.sdo_project.domain.models

import java.sql.Time

data class Grade(
    val id: Int,
    val student_id: Int,
    val point_id: Int,
    val grade: Float,
    val time: Time // проверить
)
