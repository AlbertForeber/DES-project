package com.example.sdo_project.domain.usecase.user

import com.example.sdo_project.domain.models.User
import com.example.sdo_project.domain.repository.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(uuid: String): Result<User>{
        return userRepository.getProfile(uuid)
    }
}