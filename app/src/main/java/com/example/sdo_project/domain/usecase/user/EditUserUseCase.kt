package com.example.sdo_project.domain.usecase.user

import com.example.sdo_project.domain.models.User
import com.example.sdo_project.domain.repository.UserRepository
import jakarta.inject.Inject

class EditUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(user: User) : Result<Unit> {
        return repository.editProfile(newUserData = user)
    }
}