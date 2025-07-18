package com.example.sdo_project.domain.models

data class Discipline(
    val id: Int,
    val name: String,
    val term: Int,
    val departmentId: Int,
    val courseId: Int,
    val instituteId: Int
)
