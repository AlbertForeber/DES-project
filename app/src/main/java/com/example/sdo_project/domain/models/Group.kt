package com.example.sdo_project.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Group(
    val id: Int,
    val name: String
)