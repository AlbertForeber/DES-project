package com.example.sdo_project.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GradePointDto(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("section_id") val sectionId: Int,
    @SerialName("max_score") val maxScore: Int
)
