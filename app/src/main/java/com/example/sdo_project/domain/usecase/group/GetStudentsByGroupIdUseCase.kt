package com.example.sdo_project.domain.usecase.group

import com.example.sdo_project.domain.models.User
import com.example.sdo_project.domain.repository.GroupRepository
import jakarta.inject.Inject

class GetStudentsByGroupIdUseCase @Inject constructor(
    private val repository: GroupRepository
) {
    suspend operator fun invoke( groupId: Int ): Result<List<User>> {
        return repository.getStudentsByGroupId(groupId)
    }
}