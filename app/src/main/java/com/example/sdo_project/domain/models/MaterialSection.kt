package com.example.sdo_project.domain.models

data class MaterialSection(
    val id: Int,
    val parentId: Int?,
    val disciplineId: Int,
    val name: String
)