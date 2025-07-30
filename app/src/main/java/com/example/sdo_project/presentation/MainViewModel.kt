package com.example.sdo_project.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sdo_project.domain.usecase.user.GetTokenUseCase
import com.example.sdo_project.domain.usecase.user.GetUserUseCase
import com.example.sdo_project.presentation.navgraph.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    var startDestination by mutableStateOf(Routes.AuthNavigator.route)
        private set

    var splashCondition by mutableStateOf(true)
        private set

    private val _state = MutableStateFlow<MainState>(MainState.Unauthorized)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            if ( recheck() ) splashCondition = false

        }
    }

    fun onEvent( event: MainEvent ) {
        viewModelScope.launch {
            when (event) {
                is MainEvent.Recheck -> {
                    recheck()
                }
            }
        }
    }


    private suspend fun recheck( ): Boolean {
        _state.value = MainState.Unauthorized
        Log.d("MAIN_VM_TEST", _state.value.toString())
        getTokenUseCase().fold(
            onSuccess = {
                if ( it == null ) {
                    startDestination = Routes.AuthNavigator.route
                }
                else {
                    getUser(it)
                }
                return true
            },
            onFailure = {
                return false
            }
        )
    }

    private suspend fun getUser(uuid: String) {
        getUserUseCase( uuid ).fold(
            onSuccess = {
                _state.value = MainState.Authorized(it)
                startDestination = Routes.HomeNavigator.route
            },
            onFailure = {}
        )
    }

}