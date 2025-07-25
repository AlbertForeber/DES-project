package com.example.sdo_project.data.repository

import com.example.sdo_project.domain.repository.AuthRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import jakarta.inject.Inject

class SupabaseAuthRepositoryImpl @Inject constructor(
    private val client: SupabaseClient
): AuthRepository {
    override suspend fun signUpWithEmail(email: String, password: String): Result<Unit> {
        return try {
            client.auth.signUpWith(Email) {
                this.email = email
                this.password = password
            }
            Result.success(Unit)
        }
        catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun signInWithEmail(email: String, password: String): Result<Unit> {
        return try {
            client.auth.signInWith(Email) {
                this.email = email
                this.password = password
            }
            Result.success(Unit)
        }
        catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getCurrentToken(): Result<String?> {
        return try {
            Result.success(client.auth.currentAccessTokenOrNull())
        }
        catch( e: Exception ) {
            Result.failure(e)
        }
    }

    override suspend fun logout(): Result<Unit> {
        return try {
            client.auth.signOut()
            Result.success(Unit)
        }
        catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun sendResetLinkTo(email: String): Result<Unit> {
        return try {
            client.auth.resetPasswordForEmail(email)
            Result.success(Unit)
        }
        catch (e: Exception) {
            Result.failure(e)
        }
    }
}