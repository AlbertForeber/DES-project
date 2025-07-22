package com.example.sdo_project.domain.repository

import com.example.sdo_project.domain.models.User


interface AuthRepository {
    suspend fun signUpWithEmail(email: String, password: String): Result<Unit>
    suspend fun signInWithEmail(email: String, password: String): Result<Unit>
    suspend fun getCurrentToken(): Result<String?>
    suspend fun logout(): Result<Unit>
}