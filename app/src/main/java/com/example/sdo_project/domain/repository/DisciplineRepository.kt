package com.example.sdo_project.domain.repository

import com.example.sdo_project.domain.models.Discipline
import com.example.sdo_project.domain.models.User

interface DisciplineRepository {
    suspend fun getDisciplines(user: User): Result<List<Discipline>> // дисциплины по UUID пользователя + isTeacher
}