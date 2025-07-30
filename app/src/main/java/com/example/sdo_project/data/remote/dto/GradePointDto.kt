package com.example.sdo_project.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GradePointDto(
    @SerialName("point_id") val id: Int,
    @SerialName("point_name") val name: String,
    @SerialName("point_current_score") val currentScore: Float,
    @SerialName("point_max_score") val maxScore: Int
)
