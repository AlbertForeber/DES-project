package com.example.sdo_project.domain.repository

import com.example.sdo_project.domain.models.Group
import com.example.sdo_project.domain.models.User

interface GroupRepository {
    suspend fun getGroupsByTeacherUuid(teacherUuid: String, disciplineId: Int): Result<List<Group>>

    suspend fun getStudentsByGroupId (groupId : Int): Result<List<User>>

    suspend fun getGroupById (groupId: Int): Result<Group>
}