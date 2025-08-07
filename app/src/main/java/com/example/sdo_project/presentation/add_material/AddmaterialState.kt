package com.example.sdo_project.presentation.add_material

sealed class AddMaterialState {
    object Start: AddMaterialState()
    object Loading: AddMaterialState()
    object Success: AddMaterialState()
    data class Error(val message: String): AddMaterialState()
}