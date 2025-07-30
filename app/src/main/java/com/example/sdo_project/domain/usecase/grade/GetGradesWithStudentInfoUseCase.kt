package com.example.sdo_project.domain.usecase.grade

import com.example.sdo_project.domain.models.GradeWithStudentInfo
import com.example.sdo_project.domain.repository.GradesRepository
import jakarta.inject.Inject

class GetGradesWithStudentInfoUseCase @Inject constructor(
    private val repository: GradesRepository
) {
    suspend operator fun invoke(groupId: Int, pointId: Int): Result<List<GradeWithStudentInfo>> {
        return repository.getGroupGradesByPointId(
            groupId = groupId,
            pointId = pointId
        )
    }
}