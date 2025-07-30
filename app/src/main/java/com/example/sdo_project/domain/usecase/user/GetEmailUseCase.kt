package com.example.sdo_project.domain.usecase.user

import com.example.sdo_project.domain.repository.UserRepository
import jakarta.inject.Inject

class GetEmailUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke( ) : Result<String> {
        return repository.getMail( )
    }
}