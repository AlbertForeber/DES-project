package com.example.sdo_project.presentation.material_section


import com.example.sdo_project.domain.models.MaterialSection

sealed class MaterialSectionEvent {
    data class Next(val parent: MaterialSection): MaterialSectionEvent()

    data class BackTo(val section: MaterialSection): MaterialSectionEvent()
    data class Update(val parent: MaterialSection?): MaterialSectionEvent()
}