package com.example.sdo_project.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GradeSection(
    @SerialName("section_id") val sectionId: Int,
    @SerialName("section_name") val name: String,
    @SerialName("section_max_score") val max_score: Float,
    @SerialName("section_current_score") val current_score: Float? = null
)
