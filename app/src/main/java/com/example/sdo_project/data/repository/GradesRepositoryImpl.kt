package com.example.sdo_project.data.repository

import android.util.Log
import com.example.sdo_project.data.remote.dto.GradeDto
import com.example.sdo_project.data.remote.dto.GradePointDto
import com.example.sdo_project.data.remote.dto.GradeSectionDto
import com.example.sdo_project.data.remote.dto.GradeWithStudentInfoDto
import com.example.sdo_project.domain.models.GradePoint
import com.example.sdo_project.domain.models.GradeSection
import com.example.sdo_project.domain.models.GradeWithStudentInfo
import com.example.sdo_project.domain.repository.GradesRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class GradesRepositoryImpl (
    private val client: SupabaseClient
): GradesRepository {

    // отдельная функция для проверки auth.currentUseOrNUll?

    override suspend fun getSectionsGradesByStudentId(
        studentUuid: String,
        disciplineId: Int
    ): Result<List<GradeSection>> {



        try {
            val result = client.postgrest.rpc("get_grade_sections_with_grades",
                buildJsonObject{
                    put("student_id_param", studentUuid)
                    put("discipline_id_param", disciplineId)
                }).decodeList<GradeSectionDto>().map{ it.toDomain() }

            return Result.success(result)
        }
        catch (e:Exception){
            return Result.failure(e)
        }

    }

    override suspend fun getSectionGradesByStudentId(
        studentUuid: String,
        sectionId: Int,
    ): Result<List<GradePoint>> {

        try{
            val result = client.postgrest.rpc("get_grade_point_with_grades",
                buildJsonObject {
                    put("student_id_param", studentUuid)
                    put("section_id_param", sectionId)

                }).decodeList<GradePointDto>().map { it.toDomain() }

            return Result.success(result)
        }
        catch (e:Exception){
           return  Result.failure(e)
        }

    }

    override suspend fun editGradeByStudentPointId(
        studentUuid: String,
        pointId: Int,
        score: Float
    ): Result<Unit> {
        try {
            val result = client.postgrest.from("grade").update({
                set("score", score)
            }){
                filter {
                    eq("student_id", studentUuid)
                    eq("point_id", pointId)
                }
            }
            return Result.success(Unit)
        }
        catch (e: Exception){
            return  Result.failure(e)
        }

    }

    override suspend fun getGroupGradesByPointId(
        groupId: Int,
        pointId: Int
    ): Result<List<GradeWithStudentInfo>> {
        try {
            val result = client.postgrest.rpc("get_group_grades_by_point_id",
                buildJsonObject {
                    put("point_id_param", pointId)
                    put("group_id_param", groupId)
                }).decodeList<GradeWithStudentInfoDto>().map { it.toDomain()}

            return Result.success(result)
        }
        catch (e: Exception){
            return Result.failure(e)
        }

    }

    private fun GradePointDto.toDomain(): GradePoint{
        return GradePoint(
            id = id,
            maxScore = maxScore,
            name = name,
            currentScore = currentScore
        )
    }

    private fun GradeSectionDto.toDomain(): GradeSection{
        return GradeSection(
            id = id,
            name = name,
            maxScore = maxScore,
            currentScore = curScore
        )
    }

    private fun GradeWithStudentInfoDto.toDomain(): GradeWithStudentInfo{
        return GradeWithStudentInfo(
            name = name,
            surname = surname,
            patronymic = patronymic,
            currentScore = currentScore
        )
    }

}