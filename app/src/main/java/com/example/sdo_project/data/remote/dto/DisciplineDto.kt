package com.example.sdo_project.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DisciplineDto(
    val id: Int,
    val name: String,
    val term: Int,
    @SerialName("department_id")
    val departmentId: Int,
    @SerialName("course_id")
    val courseId: Int,
    @SerialName("institute_id")
    val instituteId: Int
)