package com.example.sdo_project.domain.models

data class User(
    val uuid: String,
    val isTeacher: Boolean,
    val personalCode: String,
    val surname: String,
    val name: String,
    val patronymic: String,
    val departmentId: String,
    val country: String,
    val city: String
)