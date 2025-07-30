package com.example.sdo_project.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Discipline(
    val id: Int,
    val name: String,
    val term: Int,
    val departmentName: String,
    val courseName: String,
    val instituteName: String
) : Parcelable
