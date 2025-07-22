package com.example.sdo_project.domain.repository

import com.example.sdo_project.domain.models.GradePoint
import com.example.sdo_project.domain.models.GradeSection

interface GradesRepository {
    suspend fun getSectionsGradesByStudentId (studentUuid: String, disciplineId: Int) : Result<List<GradeSection>> // Grades Screen - main section (Attendance, Cur Control...) | Нужно получить все про секцию + балл counting from PointGrade - Grade Table
    suspend fun getSectionGradesByStudentId (studentUuid: String, sectionId: Int,disciplineId: Int): Result<List<GradePoint>> //Attendance ->  work1 .., work 2 .. | Получаем id, name, max_grade; cur_grade from Grade Table
 //   suspend fun getPointGradeByStudentId (student_id: Int,discipline_id: Int, point_id: Int ): Result<GradePoint> // Work1 ...  9/10 on the Grade Screen

}
