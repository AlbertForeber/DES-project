package com.example.sdo_project.domain.repository

import com.example.sdo_project.domain.models.Material
import com.example.sdo_project.domain.models.MaterialSection

interface MaterialRepository {
    suspend fun getMaterials(parent: MaterialSection?): Result<List<Material>>
    suspend fun addMaterial(material: Material): Result<Unit>
    suspend fun getMaterialSections(id: Int): Result<List<MaterialSection>>
}