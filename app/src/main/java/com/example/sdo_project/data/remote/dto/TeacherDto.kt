package com.example.sdo_project.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TeacherDto(
    @SerialName("uuid") val uuid: String,
    @SerialName("surname") val surname: String,
    @SerialName("name") val name: String,
    @SerialName("patronymic") val patronymic: String,
    @SerialName("department_id") val departmentId: Int,
    @SerialName("country") val country: String,
    @SerialName("city") val city: String,
    @SerialName("personal_code") val personalCode: String,
)
