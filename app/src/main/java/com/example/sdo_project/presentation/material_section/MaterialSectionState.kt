package com.example.sdo_project.presentation.material_section

import com.example.sdo_project.domain.models.Material
import com.example.sdo_project.domain.models.MaterialSection
import com.example.sdo_project.domain.models.User
import com.example.sdo_project.presentation.auth.AuthState

sealed class MaterialSectionState {
    data class Idle(
        val availableSections: List<MaterialSection>,
        val availableMaterials: List<Material>
    ): MaterialSectionState()
    data object Loading: MaterialSectionState()
    data class Error(val errorMessage: String): MaterialSectionState()
}