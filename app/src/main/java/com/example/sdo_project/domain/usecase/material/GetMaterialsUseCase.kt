package com.example.sdo_project.domain.usecase.material

import com.example.sdo_project.domain.models.Material
import com.example.sdo_project.domain.models.MaterialSection
import com.example.sdo_project.domain.repository.MaterialRepository
import javax.inject.Inject

class GetMaterialsUseCase @Inject constructor(
    private val materialRepository: MaterialRepository
) {
    suspend operator fun invoke(parent: MaterialSection): Result<List<Material>>{
        return  materialRepository.getMaterials(parent = parent)
    }
}