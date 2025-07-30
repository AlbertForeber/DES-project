package com.example.sdo_project.domain.usecase.grade

import com.example.sdo_project.domain.models.GradeTeacherPoint
import com.example.sdo_project.domain.repository.GradesRepository
import jakarta.inject.Inject

class GetPointsByDisciplineIdUseCase @Inject constructor(
    private val repository: GradesRepository
) {
    suspend operator fun invoke( disciplineId: Int ): Result<List<GradeTeacherPoint>> {
        return repository.getPointsByDisciplineId( disciplineId )
    }
}