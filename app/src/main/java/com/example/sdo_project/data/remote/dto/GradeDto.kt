package com.example.sdo_project.data.remote.dto

import kotlinx.serialization.SerialName

data class GradeDto (
    @SerialName("id") val id:Int,
    @SerialName("student_id") val studentId: Int,
    @SerialName("point_id") val pointId: Int,
    @SerialName("score") val score: Float
)