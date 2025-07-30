package com.example.sdo_project.domain.usecase.grade

import com.example.sdo_project.domain.models.GradePoint
import com.example.sdo_project.domain.repository.GradesRepository
import javax.inject.Inject

class GetSectionGradesByStudentId@Inject constructor(
    private val gradesRepository: GradesRepository
) {
    suspend operator fun invoke(studentUuid: String, sectionId: Int): Result<List<GradePoint>>{
        return gradesRepository.getSectionGradesByStudentId(studentUuid, sectionId)
    }
}
