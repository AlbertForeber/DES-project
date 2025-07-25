package com.example.sdo_project.data.repository

import android.util.Log
import com.example.sdo_project.domain.models.Group
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
}

