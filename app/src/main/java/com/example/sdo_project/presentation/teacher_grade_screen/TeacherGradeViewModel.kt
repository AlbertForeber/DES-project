package com.example.sdo_project.presentation.teacher_grade_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sdo_project.domain.models.GradeTeacherPoint
import com.example.sdo_project.domain.models.Group
import com.example.sdo_project.domain.usecase.grade.GetGradesWithStudentInfoUseCase
import com.example.sdo_project.domain.usecase.group.GetGroupsOfTeacherUseCase
import com.example.sdo_project.domain.usecase.grade.GetPointsByDisciplineIdUseCase
import com.example.sdo_project.presentation.teacher_grade_screen.components.grade_list.GradeListState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class TeacherGradeViewModel @Inject constructor(
    private val getGroupsUseCase: GetGroupsOfTeacherUseCase,
    private val getPointsUseCase: GetPointsByDisciplineIdUseCase,
    private val getGradesWithInfoUseCase: GetGradesWithStudentInfoUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<TeacherGradeState>(TeacherGradeState.Loading)
    val state = _state.asStateFlow()

    private val _listState =
        MutableStateFlow<Map<GradeTeacherPoint, GradeListState>>(mutableMapOf())
    val listState = _listState.asStateFlow()
//    var listState = mutableStateMapOf<GradeTeacherPoint, GradeListState>()
//   // val listState = _listState

    fun onEvent( event: TeacherEvent ) {
        viewModelScope.launch(Dispatchers.IO) {
            when (event) {
                is TeacherEvent.GetGrades -> {
                    getOrUnloadGradeWithInfo( event.groupId, event.pointId )
                }
                is TeacherEvent.GetGroupsAndPoints -> {
                    withContext(Dispatchers.Main) {
                        _state.value = TeacherGradeState.Loading
                        getGroupsAndPoints( event.teacherUuid, event.disciplineId )
                    }

                }
            }

        }
    }

    private suspend fun getOrUnloadGradeWithInfo(groupId: Int, pointId: Int) {
        val point = _listState.value.keys.find { key ->
            key.id == pointId
        }

        val newMap = _listState.value.toMutableMap()

        if (newMap[point!!] is GradeListState.Idle ) {
            delay(200)
            newMap[point] = GradeListState.Loading
        }

        else {
            _listState.value = newMap
            getGradesWithInfoUseCase(groupId, pointId).fold(
                onSuccess = {
                    newMap[point] = GradeListState.Idle(it)
                },
                onFailure = {
                    newMap[point] =
                        GradeListState.Error(errorMessage = it.message ?: "Unknown Error")
                }
            )
        }
        delay(200)
        _listState.value = newMap
    }


    private suspend fun getPoints(disciplineId: Int, groups: List<Group>) {
        val newMap = _listState.value.toMutableMap()
        getPointsUseCase( disciplineId ).fold(
            onSuccess = {
                it.forEach { point ->
                    newMap[point] = GradeListState.Loading
                }
                _listState.value = newMap
                _state.value = TeacherGradeState.Idle(groups)
            },
            onFailure = {
                _state.value = TeacherGradeState.Error(errorMessage = it.message ?: "Unknown error")
            }
        )
    }

    private suspend fun getGroupsAndPoints(teacherUuid: String, disciplineId: Int) {
        getGroupsUseCase(teacherUuid, disciplineId).fold(
            onSuccess = {
                withContext(Dispatchers.Main) {
                    getPoints(disciplineId, it)
                }
            },
            onFailure = {
                withContext(Dispatchers.Main) {
                    _state.value = TeacherGradeState.Error(errorMessage = it.message ?: "Unknown error")
                }
            }
        )
    }


}