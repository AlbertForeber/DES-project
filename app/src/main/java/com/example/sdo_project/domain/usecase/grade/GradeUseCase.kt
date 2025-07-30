package com.example.sdo_project.domain.usecase.grade

data class GradeUseCase(
    val editGradeByStudentPointId: EditGradeByStudentPointId,
    val getGroupGradesByPointIdUseCase: GetGroupGradesByPointIdUseCase,
    val getSectionsGradesByStudentId: GetSectionsGradesByStudentId,
    val getSectionGradesByStudentId: GetSectionGradesByStudentId
)
