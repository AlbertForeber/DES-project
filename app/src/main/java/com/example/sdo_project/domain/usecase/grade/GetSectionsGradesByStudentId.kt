package com.example.sdo_project.domain.usecase.grade

import com.example.sdo_project.domain.models.GradeSection
import com.example.sdo_project.domain.repository.GradesRepository
import javax.inject.Inject

class GetSectionsGradesByStudentId @Inject constructor(
    private val gradesRepository: GradesRepository
) {
    suspend operator fun invoke(studentUuid: String, disciplineId: Int): Result<List<GradeSection>>{
        return gradesRepository.getSectionsGradesByStudentId(studentUuid, disciplineId)
    }
}