package com.example.sdo_project.domain.usecase

import com.example.sdo_project.domain.models.User
import com.example.sdo_project.domain.repository.AuthRepository
import com.example.sdo_project.domain.repository.DisciplineRepository
import com.example.sdo_project.domain.repository.GroupRepository
import jakarta.inject.Inject

class GetGroupsOfTeacherUseCase @Inject constructor(
    private val repository: GroupRepository
) {
    suspend operator fun invoke( teacherUuid: String, disciplineId: Int ) {
        repository.getGroupsByTeacherUuid( teacherUuid, disciplineId )
    }
}