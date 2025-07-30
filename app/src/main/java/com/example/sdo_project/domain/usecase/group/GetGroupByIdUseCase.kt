package com.example.sdo_project.domain.usecase.group

import com.example.sdo_project.domain.models.Group
import com.example.sdo_project.domain.repository.GroupRepository
import javax.inject.Inject

class GetGroupByIdUseCase @Inject constructor(
    private val repository: GroupRepository
) {

    suspend operator fun invoke( groupId: Int ): Result<Group> {
        return repository.getGroupById(groupId = groupId)
    }

}