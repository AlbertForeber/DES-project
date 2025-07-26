package com.example.sdo_project.domain.usecase

import com.example.sdo_project.domain.repository.AuthRepository
import jakarta.inject.Inject

class LogoutUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(): Result<Unit> {
        return repository.logout()
    }
}