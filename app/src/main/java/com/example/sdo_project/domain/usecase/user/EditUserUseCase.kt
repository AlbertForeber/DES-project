package com.example.sdo_project.domain.usecase.user

import com.example.sdo_project.domain.models.User
import com.example.sdo_project.domain.repository.UserRepository
import javax.inject.Inject

class EditUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(user: User):Result<Unit>{
        return userRepository.editProfile(user)
    }
}