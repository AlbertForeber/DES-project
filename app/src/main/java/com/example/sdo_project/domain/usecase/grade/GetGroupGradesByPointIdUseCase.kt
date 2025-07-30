package com.example.sdo_project.domain.usecase.grade

import com.example.sdo_project.domain.models.GradeWithStudentInfo
import com.example.sdo_project.domain.repository.GradesRepository
import javax.inject.Inject

class GetGroupGradesByPointIdUseCase @Inject constructor(
    private val gradesRepository: GradesRepository
) {
    suspend operator fun invoke(groupId: Int, pointId: Int): Result<List<GradeWithStudentInfo>>{
        return gradesRepository.getGroupGradesByPointId(groupId, pointId)
    }
}