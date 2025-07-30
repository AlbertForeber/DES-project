package com.example.sdo_project.domain.usecase

import com.example.sdo_project.domain.models.Discipline
import com.example.sdo_project.domain.models.User
import com.example.sdo_project.domain.repository.AuthRepository
import com.example.sdo_project.domain.repository.DisciplineRepository
import jakarta.inject.Inject

class GetDisciplinesUseCase @Inject constructor(
    private val repository: DisciplineRepository
) {
    suspend operator fun invoke( user: User ): Result<List<Discipline>> {
        return repository.getDisciplines( user = user )
    }
}