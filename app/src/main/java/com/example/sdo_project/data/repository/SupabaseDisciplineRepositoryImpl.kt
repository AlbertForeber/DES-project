package com.example.sdo_project.data.repository

import com.example.sdo_project.data.remote.dto.DisciplineDto
import com.example.sdo_project.domain.models.Discipline
import com.example.sdo_project.domain.models.User
import com.example.sdo_project.domain.repository.DisciplineRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.exceptions.HttpRequestException
import io.github.jan.supabase.exceptions.RestException
import io.github.jan.supabase.postgrest.postgrest
import io.ktor.client.plugins.HttpRequestTimeoutException
import jakarta.inject.Inject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class SupabaseDisciplineRepositoryImpl @Inject constructor(
    private val client: SupabaseClient
) : DisciplineRepository {
    override suspend fun getDisciplines(user: User): Result<List<Discipline>> {
        return try {
            // Двоим на случай ученика и преподавателя, т.к. в одном случае нужно направление, а в другом кафедра
            val result = if (!user.isTeacher) getDisciplinesForStudent(user.uuid, client)
                else getDisciplinesForTeacher(user.uuid, client)
            Result.success(result)
        }
        catch (e: RestException) {
            Result.failure(Exception("Database issue"))
        }
        catch (e: HttpRequestTimeoutException) {
            Result.failure(Exception("Network issue: Timeout"))
        }
        catch (e: HttpRequestException) {
            Result.failure(Exception("Network issue"))
        }
        catch( e: Exception ) {
            Result.failure(e)
        }
    }
}

private suspend fun getDisciplinesForStudent(uuid: String, client: SupabaseClient): List<Discipline> {
    return client.postgrest
        .rpc(                                            // Синтаксис вызова Postgre-функции в Kotlin DSL
            "get_disciplines_by_student_uuid",
            buildJsonObject {
                put("uuid_param", uuid)                  // Создаем json-объект с параметрами для функции
            }
        ).decodeList<DisciplineDto>().map { it.toDomain() }
    // Также существует параметр request, как и в остальных функциях Supabase DB (не используем)
}
private suspend fun getDisciplinesForTeacher(uuid: String, client: SupabaseClient): List<Discipline> {
    return client.postgrest
        .rpc(
            "get_disciplines_by_teacher_uuid",
            buildJsonObject {
                put("uuid_param", uuid)
            }
        ).decodeList<DisciplineDto>().map { it.toDomain() }
}

private fun DisciplineDto.toDomain(): Discipline {
    return Discipline(
        name = this.name,
        id = this.id,
        term = this.term,
        departmentName = this.departmentName,
        courseName = this.courseName,
        instituteName = this.instituteName,
    )
}
