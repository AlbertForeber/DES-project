package com.example.sdo_project.domain.repository

import com.example.sdo_project.domain.models.Discipline

interface DisciplineRepository {
    suspend fun getDisciplines(): Result<List<Discipline>>
}