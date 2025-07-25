package com.example.sdo_project.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.sdo_project.domain.usecase.AuthUseCases
import jakarta.inject.Inject

class `MainViewModel\` @Inject constructor(
    private val authUseCases: AuthUseCases
) : ViewModel {
    private val _state = mutableStateOf(mutableStateOf)
}