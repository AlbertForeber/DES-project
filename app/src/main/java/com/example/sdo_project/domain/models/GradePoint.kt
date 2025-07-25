package com.example.sdo_project.domain.models

import kotlinx.serialization.Serializable

data class GradePoint(
    val id: Int,
    val name: String,
    val discipline_id: Int,
    val section_id: Int,
    val max_grade: Int,
    val cur_grade: Float? = null
)
