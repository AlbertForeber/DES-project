package com.example.sdo_project.domain.repository

import com.example.sdo_project.data.remote.dto.GradeWithStudentInfoDto
import com.example.sdo_project.domain.models.GradePoint
import com.example.sdo_project.domain.models.GradeSection
import com.example.sdo_project.domain.models.GradeWithStudentInfo

interface GradesRepository {
    suspend fun getSectionsGradesByStudentId(studentUuid: String, disciplineId: Int): Result<List<GradeSection>> // Grades Screen - main section (Attendance, Cur Control...) | Нужно получить все про секцию + балл counting from PointGrade - Grade Table
    suspend fun getSectionGradesByStudentId(studentUuid: String, sectionId: Int): Result<List<GradePoint>> //Current control ->  work1 .., work 2 .. | Получаем id, name, max_grade; cur_grade from Grade Table
    suspend fun editGradeByStudentPointId (studentUuid: String, pointId: Int, score: Float): Result<Unit>
    suspend fun getGroupGradesByPointId (groupId: Int, pointId: Int) : Result<List<GradeWithStudentInfo>> // student - ФИО, cur_score

}