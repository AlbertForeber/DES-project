package com.example.sdo_project.domain.usecase

import com.example.sdo_project.domain.repository.AuthRepository
import jakarta.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke( email: String, password: String ): Result<Unit> {
        return repository.signInWithEmail( email, password )
    }
}