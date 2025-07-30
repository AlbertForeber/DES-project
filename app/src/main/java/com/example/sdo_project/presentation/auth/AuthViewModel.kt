package com.example.sdo_project.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sdo_project.domain.usecase.auth.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCases: AuthUseCases
) : ViewModel() {

    private val _state = MutableStateFlow<AuthState>(AuthState.Idle)
    val state = _state.asStateFlow()

    fun onEvent( event: AuthEvent ) {
        viewModelScope.launch {
            when (event) {
                is AuthEvent.Login -> {
                    withContext(Dispatchers.Main) {
                        _state.value = AuthState.Loading
                        login(event.email, event.password)
                    }

                }

                is AuthEvent.Reset -> {
                    reset( event.email )
                }
            }
        }
    }


    private suspend fun errorHandler( exception: Throwable ) {
        withContext(Dispatchers.Main) {
            _state.value = AuthState.Error(exception.message ?: "Unknown error")
            delay(300)
            _state.value = AuthState.Idle
        }
    }

    private suspend fun reset( email: String ) {
        val result = authUseCases.reset(email)
        result.fold(
            onSuccess = {
                errorHandler(Exception("Reset password email sent"))
            },
            onFailure = {
                errorHandler(it)
            }
        )
    }

    private suspend fun login( email: String, password: String ) {
        val result = authUseCases.login(email, password)
        result.fold(
            onSuccess = {
                _state.value = AuthState.Authorized
            },
            onFailure = {
                errorHandler(it)
            }
        )
    }
}