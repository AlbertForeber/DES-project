package com.example.sdo_project.data.repository

import com.example.sdo_project.domain.models.GradePoint
import com.example.sdo_project.domain.models.GradeSection
import com.example.sdo_project.domain.repository.GradesRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.postgrest
import io.ktor.http.parameters

class GradesRepositoryImpl (
    private val client: SupabaseClient
): GradesRepository {

    // отдельная функция для проверки auth.currentUseOrNUll?

    override suspend fun getSectionsGradesByStudentId(
        studentUuid: String,
        disciplineId: Int
    ): Result<List<GradeSection>> {

        val currentUuid = client.auth.currentUserOrNull()?.id
        if (currentUuid == null) return  Result.failure(throw Exception("no current user - GradesRepositoryImpl"))

        try {
            val result =     client.postgrest.rpc("get_grade_sections_with_grades"){
                parameters {
                    buildMap {
                        put("student_id_param", studentUuid)
                        put("discipline_id_param", disciplineId)
                    }
                }
            }.decodeList<GradeSection>()
            return Result.success(result)
        }
        catch (e:Exception){
            return Result.failure(e)
        }

    }

    override suspend fun getSectionGradesByStudentId(
        studentUuid: String,
        sectionId: Int,
        disciplineId: Int
    ): Result<List<GradePoint>> {

        val current_uuid = client.auth.currentUserOrNull()?.id
        if (current_uuid == null) return  Result.failure(throw Exception("no current user - GradesRepositoryImpl"))

        try{
            val result = client.postgrest.rpc("get_grade_point_with_grades"){
                parameters {
                    buildMap {
                        put("student_id_param", studentUuid)
                        put("discipline_id_param", disciplineId)
                        put("section_id_param", sectionId)

                    }
                }
            }.decodeList<GradePoint>()
            return Result.success(result)
        }
        catch (e:Exception){
           return  Result.failure(e)
        }

    }

}