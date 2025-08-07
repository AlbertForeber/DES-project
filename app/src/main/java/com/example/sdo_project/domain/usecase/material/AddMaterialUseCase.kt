package com.example.sdo_project.domain.usecase.material

import com.example.sdo_project.domain.models.Material
import com.example.sdo_project.domain.models.MaterialSection
import com.example.sdo_project.domain.repository.MaterialRepository
import javax.inject.Inject

class AddMaterialUseCase @Inject constructor(
    private val materialRepository: MaterialRepository
) {

    suspend operator fun invoke(material: Material): Result<Unit>{
       return materialRepository.addMaterial(material =  material)
    }
}