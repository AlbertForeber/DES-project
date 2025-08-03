package com.example.sdo_project.domain.usecase.material

import com.example.sdo_project.domain.models.MaterialSection
import com.example.sdo_project.domain.repository.MaterialRepository
import javax.inject.Inject

class GetMaterialSectionsUseCase @Inject constructor(
    private val materialRepository: MaterialRepository
) {
    suspend operator fun invoke(disciplineId: Int, parentId: Int? = null): Result<List<MaterialSection>>{
       return  materialRepository.getMaterialSections(disciplineId = disciplineId, parentId =  parentId)
    }
}