package com.example.sdo_project.presentation.teacher_grade_screen.components.change_grade

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sdo_project.domain.usecase.grade.ChangeGradeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class ChangeGradeViewModel @Inject constructor(
    private val changeGradeUseCase: ChangeGradeUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<ChangeGradeState>(ChangeGradeState.Idle)
    val state = _state.asStateFlow()

    fun onEvent(event: ChangeGradeEvent) {
        viewModelScope.launch(Dispatchers.IO) {
            when (event) {
                ChangeGradeEvent.BackToIdle -> {
                    withContext(Dispatchers.Main) {
                        delay(100)
                        _state.value = ChangeGradeState.Idle
                    }
                }
                is ChangeGradeEvent.ChangeGrade -> changeGrade(
                    event.newGrade,
                    event.pointId,
                    event.uuid
                )
            }

        }
    }

    private suspend fun changeGrade(newGrade: Float, pointId: Int, uuid: String) {
        withContext(Dispatchers.Main) {
            _state.value = ChangeGradeState.Loading
        }
        changeGradeUseCase(
            uuid = uuid,
            pointId = pointId,
            score = newGrade
        ).fold(
            onSuccess = {
                withContext(Dispatchers.Main) {
                    _state.value = ChangeGradeState.Success
                }

            },
            onFailure = {
                withContext(Dispatchers.Main) {
                    _state.value = ChangeGradeState.Error( it.message ?: "Unknown error" )
                }
            }
        )
    }
}