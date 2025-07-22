package com.example.sdo_project.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MaterialDto (
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("section_id") val sectionId: Int,
    @SerialName("access_time") val accessTime: String,
    @SerialName("content") val content: String,

    )