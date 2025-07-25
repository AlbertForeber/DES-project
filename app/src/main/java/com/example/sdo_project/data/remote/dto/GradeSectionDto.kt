package com.example.sdo_project.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GradeSectionDto(
    @SerialName("section_id") val id: Int,
    @SerialName("section_name") val name: String,
    @SerialName("section_max_score") val maxScore: Int,
    @SerialName("section_current_score") val curScore: Float,
)
