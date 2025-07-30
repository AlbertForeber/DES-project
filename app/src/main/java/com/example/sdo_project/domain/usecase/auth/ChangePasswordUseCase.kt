package com.example.sdo_project.domain.usecase.auth

import com.example.sdo_project.domain.repository.AuthRepository
import jakarta.inject.Inject

class ChangePasswordUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke( password: String ): Result<Unit> {
        return repository.changePassword( password )
    }
}