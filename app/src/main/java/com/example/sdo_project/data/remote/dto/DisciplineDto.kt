package com.example.sdo_project.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DisciplineDto(
    val id: Int,
    val name: String,
    val term: Int,
    @SerialName("department_name")
    val departmentName: String,
    @SerialName("course_name")
    val courseId: String,
    @SerialName("institute_name")
    val instituteName: String
)