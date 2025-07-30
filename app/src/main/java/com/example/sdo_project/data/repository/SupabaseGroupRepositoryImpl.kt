package com.example.sdo_project.data.repository

import android.util.Log
import com.example.sdo_project.data.remote.dto.StudentDto
import com.example.sdo_project.domain.models.Group
import com.example.sdo_project.domain.models.User
import com.example.sdo_project.domain.repository.GroupRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.postgrest.rpc
import jakarta.inject.Inject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class SupabaseGroupRepositoryImpl @Inject constructor(
    private val client: SupabaseClient
) : GroupRepository {

    override suspend fun getGroupsByTeacherUuid(teacherUuid: String, disciplineId: Int): Result<List<Group>> {
        return try {
            val result = client.postgrest.rpc(
                "get_groups_by_teacher_uuid",
                buildJsonObject {
                    put("teacher_uuid_param", teacherUuid)
                    put("discipline_id_param", disciplineId)
                }
            )
            Log.d("DB_TEST", result.data)
            Result.success(result.decodeList<Group>())
        }
        catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getStudentsByGroupId(groupId: Int): Result<List<User>> {
        return try {
            val result = client.from("student").select {
                filter {
                    eq("group_id", groupId)
                }
            }.decodeList<StudentDto>().map { it.toDomain() }

            Result.success(result)
        }
        catch (e: Exception){
             Result.failure(e)
        }
    }

    override suspend fun getGroupById(groupId: Int): Result<Group> {
        return try {
            val result = client.from("group").select {
                filter {
                    eq("id", groupId)
                }
            }.decodeSingle<Group>()

            Result.success(result)
        }
        catch (e: Exception){
            Result.failure(e)
        }



    }

    private fun StudentDto.toDomain():User {
        return User(
            uuid = uuid,
            isTeacher = false,
            personalCode = personalCode,
            surname = surname,
            name = name,
            patronymic = patronymic,
            departmentId = groupId,
            country = country,
            city = city
        )
    }


}

