package com.example.sdo_project.domain.repository

interface AuthRepository {
    suspend fun signUpWithEmail(email: String, password: String) //: Result<User>
    suspend fun signInWithEmail(email: String, password: String):Result<Boolean>
    suspend fun  getCurrentUser() //: User?
}