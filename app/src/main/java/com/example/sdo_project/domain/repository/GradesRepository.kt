package com.example.sdo_project.domain.repository

import com.example.sdo_project.domain.models.GradePoint
import com.example.sdo_project.domain.models.GradeSection

interface GradesRepository {
    suspend fun getSectionsGradesByStudentId (student_id: Int, discipline_id: Int) : Result<GradeSection> // Grades Screen - main section (Attendance, Cur Control...) | Нужно получить все про секцию + балл counting from PointGrade - Grade Table
    suspend fun getSectionGradesByStudentId (student_id: Int, section_id: Int): Result<List<GradePoint>> //Attendance ->  work1 .., work 2 .. | Получаем id, name, max_grade; cur_grade from Grade Table
    suspend fun getPointGradeByStudentId (student_id: Int,discipline_id: Int, point_id: Int ): Result<GradePoint> // Work1 ...  9/10 on the Grade Screen
    //suspend fun getFullPointGradeByStudentId (student_id: Int, point_id: Int ): Result <> // Screen for an a Point Screen
}