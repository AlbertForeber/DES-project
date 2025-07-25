package com.example.sdo_project.domain.models

data class MaterialSection(
    val id: Int,
    val name: String,
    val parentId: Int?,
    val disciplineId: Int,

)