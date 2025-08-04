package com.example.sdo_project.presentation.participant

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sdo_project.domain.models.Group
import com.example.sdo_project.domain.models.User
import com.example.sdo_project.domain.usecase.group.GetGroupByIdUseCase
import com.example.sdo_project.domain.usecase.group.GetGroupsOfTeacherUseCase
import com.example.sdo_project.domain.usecase.group.GetStudentsByGroupIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ParticipantViewModel @Inject constructor(
    private val groupsOfTeacherUseCase: GetGroupsOfTeacherUseCase,
    private val getStudentsByGroupIdUseCase: GetStudentsByGroupIdUseCase,
    private val getGroupByIdUseCase: GetGroupByIdUseCase
):ViewModel() {

    // groups for teacher to choose
    private val _groups = mutableStateOf<ParticipantState>(ParticipantState.Start)
    val groups: State<ParticipantState> = _groups

    // participants of chosen by teacher-user group / students of student-user`s group
//    private val _participants = mutableStateOf<List<User>>(emptyList())
//    val participants: State<List<User>> = _participants

    private val _participants = mutableStateOf<ParticipantStudentListState>(ParticipantStudentListState.Start)
    val participants: State<ParticipantStudentListState> = _participants

    private val _chosen_group = mutableStateOf<Group?>(null)
    val chosen_group : State<Group?> = _chosen_group

    fun getGroupsByTeacherId( teacherUuid: String, disciplineId: Int){
        _groups.value = ParticipantState.Loading
        viewModelScope.launch (Dispatchers.IO){
            groupsOfTeacherUseCase(teacherUuid = teacherUuid, disciplineId = disciplineId).onSuccess { list ->
                withContext(Dispatchers.Main){
                    _groups.value = ParticipantState.Success(groups = list)

                }

            }.onFailure { error ->
                _groups.value = ParticipantState.Error(message = error.message ?: "Unknown error")
                Log.d("SUPABASE_DB_LOGS", "ParticipantViewModele list getGroupByTeacherId: ${error.message}")

            }
        }
    }

    fun getStudentsByGroupId (groupId: Int) {
        viewModelScope.launch(Dispatchers.IO) {

            getStudentsByGroupIdUseCase(groupId = groupId).onSuccess { list ->
                getGroupByIdUseCase(groupId = groupId).onSuccess { group ->

                        withContext(Dispatchers.Main){
                            //_participants.value = list
                            _participants.value = ParticipantStudentListState.Success(participants = list)
                            _chosen_group.value = group
                        }

                }.onFailure { error ->
                    // maybe make another error so that Toastb will be another
                    _participants.value = ParticipantStudentListState.Error(message = error.message ?: "Unknown error")
                    Log.d("SUPABASE_DB_LOGS", "ParticipantViewModele group getStudentsByGroupId: ${error.message}")
                }



            }.onFailure { error ->
                // maybe make another error so that Toastb will be another
                _participants.value = ParticipantStudentListState.Error(message = error.message ?: "Unknown error")
                Log.d("SUPABASE_DB_LOGS", "ParticipantViewModele list getStudentsByGroupId: ${error.message}")
            }

        }
    }




}