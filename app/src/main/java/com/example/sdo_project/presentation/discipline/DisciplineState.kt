package com.example.sdo_project.presentation.discipline

import com.example.sdo_project.domain.models.MaterialSection

sealed class DisciplineState {

    object Start: DisciplineState()
    object Loading: DisciplineState()
    data class Success( val materialSections: List<MaterialSection>): DisciplineState()

}