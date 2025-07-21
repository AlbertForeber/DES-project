package com.example.sdo_project.domain.repository

import com.example.sdo_project.domain.models.Discipline

interface DisciplineRepository {
    suspend fun getDisciplines(uuid: String): Result<List<Discipline>> // дисциплины по UUID пользователя
}