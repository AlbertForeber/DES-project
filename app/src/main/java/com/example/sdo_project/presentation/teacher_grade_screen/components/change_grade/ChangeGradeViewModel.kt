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

    fun changeGrade(newGrade: Float, pointId: Int, uuid: String) {
        viewModelScope.launch(Dispatchers.IO) {
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
                        delay(100)
                        _state.value = ChangeGradeState.Idle
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
}