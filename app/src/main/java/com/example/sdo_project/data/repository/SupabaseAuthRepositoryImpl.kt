package com.example.sdo_project.data.repository

import com.example.sdo_project.domain.repository.AuthRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.exception.AuthRestException
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.exceptions.HttpRequestException
import io.github.jan.supabase.exceptions.RestException
import io.ktor.client.plugins.HttpRequestTimeoutException
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
        catch (e: RestException) {
            Result.failure(e)
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

    override suspend fun signInWithEmail(email: String, password: String): Result<Unit> {
        return try {
            client.auth.signInWith(Email) {
                this.email = email
                this.password = password
            }
            Result.success(Unit)
        }
        catch (e: RestException) {
            val exception = Exception(when {
                e.message?.contains("validation_failed") == true -> "Fields must be filled"
                e.message?.contains("invalid_credentials") == true -> "Email or password is invalid"
                e.message?.contains("Email must not be blank") == true -> "Email must not be blank"
                else -> "Unknown error"
            })
            Result.failure(exception)
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

    override suspend fun getCurrentToken(): Result<String?> {
        return try {
            Result.success(client.auth.currentUserOrNull()?.id)
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
        catch (e: RestException) {
            Result.failure(e)
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

    override suspend fun sendResetLinkTo(email: String): Result<Unit> {
        return try {
            client.auth.resetPasswordForEmail(email)
            Result.success(Unit)
        }
        catch (e: RestException) {
            Result.failure(e)
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

    override suspend fun changePassword(password: String): Result<Unit> {
        return try {
            client.auth.updateUser {
                this.password = password
            }
            Result.success(Unit)
        }
        catch (e: RestException) {
            Result.failure(e)
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