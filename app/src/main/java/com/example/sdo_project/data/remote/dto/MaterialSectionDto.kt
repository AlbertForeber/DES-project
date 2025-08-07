package com.example.sdo_project.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MaterialSectionDto(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("parent_id") val parentId: Int?,
    @SerialName("discipline_id") val disciplineId: Int,
)

@Serializable
data class NewMaterialSectionDto (
    @SerialName("name") val name: String,
    @SerialName("parent_id") val parentId: Int? = null,
    @SerialName("discipline_id") val disciplineId: Int,


    )