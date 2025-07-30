package com.example.sdo_project.domain.usecase.auth

import com.example.sdo_project.domain.repository.AuthRepository
import jakarta.inject.Inject

class SignupUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke( email: String, password: String ) {
        repository.signUpWithEmail( email, password )
    }
}