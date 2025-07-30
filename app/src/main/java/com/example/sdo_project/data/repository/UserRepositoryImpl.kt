package com.example.sdo_project.data.repository

import android.util.Log
import androidx.compose.runtime.traceEventEnd
import com.example.sdo_project.data.remote.dto.StudentDto
import com.example.sdo_project.data.remote.dto.TeacherDto
import com.example.sdo_project.data.remote.dto.UserDto
import com.example.sdo_project.domain.models.User
import com.example.sdo_project.domain.repository.UserRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.from
import java.nio.file.AccessDeniedException

class UserRepositoryImpl(
    private val supabaseClient: SupabaseClient
): UserRepository {

    override suspend fun getProfile(uuid: String): Result<User> {
        val userBaseInfo = supabaseClient.from("user")
            .select {
                filter {
                    eq("uuid", uuid)
                }
            }.decodeSingleOrNull<UserDto>()

        return try {
            when (userBaseInfo?.isTeacher) {
                false -> {
                  Result.success(getStudentInfo(userBaseInfo.uuid))
                }
                true -> {
                   Result.success(getTeacherInfo(userBaseInfo.uuid))
                }
                else -> {
                    Result.failure( NoSuchElementException("No user was found with uuid: $uuid") )
                }
            }
        } catch (e: Exception){

            return Result.failure(e)
        }

    }

    override suspend fun editProfile(newUserData: User): Result<Unit> {

        return try {
            when(newUserData.isTeacher ) {
                true -> {
                    supabaseClient.from("teacher")
                        .update(newUserData){
                            filter {
                                eq("uuid", newUserData.uuid )
                            }
                        }

                }
                false -> {
                    supabaseClient.from("student")
                        .update(newUserData.toStudentDto()){
                            filter {
                                eq("uuid", newUserData.uuid )
                            }
                        }

                }
            }

            Result.success(Unit)
        }
        catch (e: Exception){
            Result.failure(e)
        }


    }

    override suspend fun getMail(): Result<String> {
        return try {
            val result = supabaseClient.auth.currentUserOrNull()?.email
            return if (result != null ) Result.success(result) else Result.failure(
                AccessDeniedException("Not authorized")
            )
        }
        catch(e: Exception) {
            Result.failure(e)
        }
    }

    private suspend fun getStudentInfo(uuid: String): User{
        return supabaseClient.from("student").select {
            filter {
                eq("uuid", uuid)
            }
        }.decodeSingle<StudentDto>().toDomain()

    }

    private suspend fun getTeacherInfo(uuid: String): User{
        return supabaseClient.from("teacher").select {
            filter {
                eq("uuid", uuid)
            }
        }.decodeSingle<TeacherDto>().toDomain()
    }

    private fun TeacherDto.toDomain(): User {
        return User(
            uuid = uuid,
            isTeacher = true,
            personalCode = personalCode,
            surname = surname,
            name = name,
            patronymic = patronymic,
            departmentId = departmentId,
            country = country,
            city = city
        )
    }

    private fun StudentDto.toDomain(): User {
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

    private fun User.toStudentDto(): StudentDto {
        return StudentDto(
            uuid = uuid,
            surname = surname,
            name = name,
            patronymic = patronymic,
            groupId = departmentId,
            country = country,
            city = city,
            personalCode = personalCode
        )
    }


}