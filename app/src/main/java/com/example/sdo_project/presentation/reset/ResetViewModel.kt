package com.example.sdo_project.presentation.reset

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sdo_project.domain.usecase.auth.ChangePasswordUseCase
import com.example.sdo_project.domain.usecase.user.GetTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class ResetViewModel @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase,
    private val changePasswordUseCase: ChangePasswordUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<ResetState>(ResetState.Loading)
    val state = _state.asStateFlow()

    fun onEvent( event: ResetEvent ) {
        viewModelScope.launch {
            when (event) {
                is ResetEvent.ChangePassword -> {
                    _state.value = ResetState.Loading
                    changePassword( event.password )
                }
                ResetEvent.Check -> {
                    checkIfAuthorized()
                }
            }
        }
    }

    private suspend fun checkIfAuthorized() {
        withContext(Dispatchers.Main) {
            _state.value = if (getTokenUseCase().getOrNull() != null) ResetState.Allowed
            else ResetState.WrongLink // TODO: доп. логика?
            Log.d("AUTH_TEST",_state.value.toString())
        }
    }

    private suspend fun changePassword(password: String) {
        changePasswordUseCase(password).fold(
            onSuccess = {
                withContext(Dispatchers.Main) {
                    _state.value = ResetState.Success
                }
            },
            onFailure = {
                errorHandler(it)
            }
        )
    }

    private suspend fun errorHandler( exception: Throwable ) {
        withContext(Dispatchers.Main) {
            _state.value = ResetState.Error(exception.message ?: "Unknown error")
            delay(300)
            _state.value = ResetState.Allowed
        }
    }
}