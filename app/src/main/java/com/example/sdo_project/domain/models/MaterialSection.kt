package com.example.sdo_project.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MaterialSection(
    val id: Int,
    val name: String,
    val parentId: Int?,
    val disciplineId: Int,
) : Parcelable