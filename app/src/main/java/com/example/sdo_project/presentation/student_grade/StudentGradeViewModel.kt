package com.example.sdo_project.presentation.student_grade

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sdo_project.domain.models.GradePoint
import com.example.sdo_project.domain.models.GradeSection
import com.example.sdo_project.domain.models.User
import com.example.sdo_project.domain.usecase.auth.AuthUseCases
import com.example.sdo_project.domain.usecase.grade.GradeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class StudentGradeViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    private val gradeUseCase: GradeUseCase

): ViewModel(){

    // загрузка GradeSection
    private val _state = mutableStateOf<StudentGradeState>(StudentGradeState.Start)
    val state: State<StudentGradeState> = _state

    // хранение в Map пунктов секций, ключами будут id секций
    private val _pointsGrades = mutableStateOf<Map<Int, List<GradePoint>?>>(emptyMap())
    val pointsGrades: State<Map<Int, List<GradePoint>?>> = _pointsGrades


    fun onEvent( section: GradeSection, user: User){

        viewModelScope.launch(Dispatchers.IO) {

                gradeUseCase.getSectionGradesByStudentId(studentUuid = user.uuid, sectionId = section.id).onSuccess { list ->

                    withContext(Dispatchers.Main){

                        _pointsGrades.value = buildMap {
                            putAll(_pointsGrades.value)
                            put(section.id, list)
                        }
                    }


                }.onFailure { error ->
                    Log.d("SUPABASE_DB_LOGS", "StudentGradeViewModel list  onEvent: ${error.message}")
                }


        }

    }

    fun onLoading(disciplineId : Int, user: User){
        _state.value = StudentGradeState.Loading
        viewModelScope.launch(Dispatchers.IO) {


                gradeUseCase.getSectionsGradesByStudentId(studentUuid = user.uuid, disciplineId = disciplineId).onSuccess { list ->
                    withContext(Dispatchers.Main){
                        _state.value = StudentGradeState.Success(sectionGradeList = list)
                        _pointsGrades.value = createMapWithNoValues(list)
                    }

                }.onFailure { error ->
                   //  _state.value = StudentGradeState. // Error state
                    Log.d("SUPABASE_DB_LOGS", "StudentGradeViewModel list onLoading: ${error.message}")
                }


        }


    }

    // создание Map  ключами - id GradeSection, значениями - null
    private fun createMapWithNoValues( list: List<GradeSection>): Map<Int, List<GradePoint>?>{

        val _map = mutableMapOf<Int, List<GradePoint>?>()
        list.map{ item ->
            _map.put(item.id, null)

        }

        return _map.toMap()
    }
}