package com.example.sdo_project.domain.repository

import com.example.sdo_project.domain.models.Group

interface GroupRepository {
    suspend fun getGroupsByTeacherUuid(teacherUuid: String, disciplineId: Int): Result<List<Group>>
}