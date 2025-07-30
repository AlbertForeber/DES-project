package com.example.sdo_project.domain.usecase.group

import com.example.sdo_project.domain.models.Group
import com.example.sdo_project.domain.repository.GroupRepository
import jakarta.inject.Inject

class GetGroupsOfTeacherUseCase @Inject constructor(
    private val repository: GroupRepository
) {
    suspend operator fun invoke( teacherUuid: String, disciplineId: Int ): Result<List<Group>> {
        return repository.getGroupsByTeacherUuid( teacherUuid, disciplineId )
    }
}