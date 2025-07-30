package com.example.sdo_project.domain.usecase.grade

import com.example.sdo_project.domain.repository.GradesRepository
import jakarta.inject.Inject

class ChangeGradeUseCase @Inject constructor(
    private val repository: GradesRepository
) {
    suspend operator fun invoke(uuid: String, pointId: Int, score: Float): Result<Unit> {
        return repository.editGradeByStudentPointId(
            studentUuid = uuid,
            pointId = pointId,
            score = score
        )
    }
}