package com.example.sdo_project.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto (
    @SerialName("uuid") val uuid: String,
    @SerialName("is_teacher") val isTeacher: Boolean,
)