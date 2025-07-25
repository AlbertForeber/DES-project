package com.example.sdo_project.domain.models


data class Material (
    var id: Int = 0,
    val name: String,
    val sectionId: Int,
    val accessTime: String,
    val content: String
)
