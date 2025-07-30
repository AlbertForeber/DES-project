package com.example.sdo_project.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sdo_project.domain.models.User
import com.example.sdo_project.domain.usecase.user.EditUserUseCase
import com.example.sdo_project.domain.usecase.user.GetEmailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val editUserUseCase: EditUserUseCase,
    private val getMail: GetEmailUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<ProfileState>(ProfileState.Loading)
    val state = _state.asStateFlow()

    private var email = ""
    init {
        viewModelScope.launch(Dispatchers.IO) {
            email = getMail().getOrDefault("Loading error")
            withContext(Dispatchers.Main) {
                _state.value = ProfileState.Idle(email)
            }
        }
    }

    fun onEvent( event: ProfileEvent ) {
        viewModelScope.launch(Dispatchers.IO) {
            when (event) {
                is ProfileEvent.EditUser -> editUser(event.user)
            }
        }
    }

    private suspend fun editUser(user: User) {
        _state.value = ProfileState.Loading
        editUserUseCase(user).fold(
            onSuccess = {
                successHandler()
            },
            onFailure = {
                errorHandler(it)
            }
        )
    }

    private suspend fun errorHandler(exception: Throwable) {
        withContext(Dispatchers.Main) {
            _state.value = ProfileState.Error(exception.message ?: "Unknown error", email)
            delay(300)
            _state.value = ProfileState.Idle(email)
        }
    }

    private suspend fun successHandler() {
        withContext(Dispatchers.Main) {
            _state.value = ProfileState.SuccessEdit
            delay(300)
            _state.value = ProfileState.Idle(email)
        }
    }
}