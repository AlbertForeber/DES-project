package com.example.sdo_project.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GradeSectionDto(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("max_score") val maxScore: Int,
)
