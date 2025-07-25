package com.example.sdo_project.domain.repository

import com.example.sdo_project.domain.models.User

interface UserRepository {
    suspend fun getProfile(uuid: String): Result<User?>
    suspend fun editProfile(newUserData: User): Result<Unit>
}