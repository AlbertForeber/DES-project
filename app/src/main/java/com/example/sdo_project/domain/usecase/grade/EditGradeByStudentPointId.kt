package com.example.sdo_project.domain.usecase.grade

import com.example.sdo_project.domain.repository.GradesRepository
import javax.inject.Inject

class EditGradeByStudentPointId @Inject constructor(
    private val gradesRepository: GradesRepository
) {
    suspend operator fun invoke(studentUuid: String, pointId: Int, score: Float): Result<Unit>{
        return gradesRepository.editGradeByStudentPointId(studentUuid, pointId, score)
    }
}