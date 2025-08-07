package com.example.sdo_project.domain.usecase.material

import com.example.sdo_project.domain.models.MaterialSection
import com.example.sdo_project.domain.repository.MaterialRepository
import javax.inject.Inject

class AddMaterialSectionsUseCase @Inject constructor(
    private val materialRepository: MaterialRepository
) {

    suspend operator fun invoke(sections: List<MaterialSection>, initialParent: Int? = null ): Result<Unit>{
        return materialRepository.addMaterialSections(sections =  sections,initialParent = initialParent )
    }

}