package com.example.sdo_project.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GradeWithStudentInfoDto(
    @SerialName("student_uuid") val uuid: String,
    @SerialName("student_name") val name: String,
    @SerialName("student_surname") val surname: String,
    @SerialName("student_patronymic") val patronymic: String,
    @SerialName("student_current_score") val currentScore: Float,

)
