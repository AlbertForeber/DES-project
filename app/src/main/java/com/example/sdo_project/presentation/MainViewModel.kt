package com.example.sdo_project.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sdo_project.domain.usecase.AuthUseCases
import com.example.sdo_project.presentation.auth.AuthEvent
import com.example.sdo_project.presentation.auth.AuthState
import com.example.sdo_project.presentation.navgraph.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.auth.Auth
import jakarta.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.Exception

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authUseCases: AuthUseCases
) : ViewModel() {

    var startDestination by mutableStateOf(Routes.AuthNavigator.route)
        private set

    var splashCondition by mutableStateOf(true)
        private set

    private val _state = MutableStateFlow<AuthState>(AuthState.Loading)
    val state = _state.asStateFlow()
    init {
        viewModelScope.launch {
            getToken()
        }
    }

    fun onEvent( event: AuthEvent ) {
        viewModelScope.launch {
            when (event) {
                is AuthEvent.Login -> {
                    _state.value = AuthState.Loading
                    login(event.email, event.password)
                }

                is AuthEvent.Logout -> {
                    logout()
                }

                is AuthEvent.Reset -> {
                    reset( event.email )
                }

                is AuthEvent.ChangePassword -> {
                    _state.value = AuthState.Loading
                     changePassword( event.password )
                }
                is AuthEvent.Recheck -> {
                    _state.value = AuthState.Loading
                    recheck(event.isReset)
                }
            }
        }
    }


    private suspend fun errorHandler( exception: Throwable ) {
        _state.value = AuthState.Error(exception.message ?: "Unknown error")
        delay(300)
        _state.value = AuthState.Idle
    }

    private suspend fun changePassword(password: String) {
        val result = authUseCases.changePass(password)
        result.fold(
            onSuccess = {
                getToken()
            },
            onFailure = {
                errorHandler(it)
            }
        )
    }

    private suspend fun recheck( isReset: Boolean ) {

        val result = authUseCases.getToken()
        result.fold(
            onSuccess = {
                if ( it == null ) {
                    startDestination = Routes.AuthNavigator.route
                    val error = if (isReset) "Link is expired" else "Unauthorized"
                    errorHandler(Exception(error))
                }
                else {
                    _state.value = AuthState.Authorized(it)
                }
            },
            onFailure = {
                startDestination = Routes.AuthNavigator.route
                errorHandler(it)
            }
        )
    }

    private suspend fun reset( email: String ) {
        val result = authUseCases.reset(email)
        result.fold(
            onSuccess = {
                _state.value = AuthState.Error("Reset password email sent")
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
                getToken()
            },
            onFailure = {
                errorHandler(it)
            }
        )
    }

    private suspend fun logout() {
        val result = authUseCases.logout()
        result.fold(
            onSuccess = {
                getToken()
            },
            onFailure = {
                errorHandler(it)
            }
        )
    }

    private suspend fun getToken() {
        val result = authUseCases.getToken()
        result.fold(
            onSuccess = {
                if ( it == null ) {
                    _state.value = AuthState.Idle
                    startDestination = Routes.AuthNavigator.route
                }
                else {
                    _state.value = AuthState.Authorized(it)
                    startDestination = Routes.HomeNavigator.route
                }
                splashCondition = false
            },
            onFailure = {
                errorHandler(it)
            }
        )
    }
}