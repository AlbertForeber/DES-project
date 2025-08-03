package com.example.sdo_project.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class GradeTeacherPointDto (
    val id: Int,
    val name: String,
    val maxScore: Float
)