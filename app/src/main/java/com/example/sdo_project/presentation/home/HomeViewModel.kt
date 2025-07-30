package com.example.sdo_project.presentation.home

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sdo_project.domain.models.User
import com.example.sdo_project.domain.usecase.GetDisciplinesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val disciplineUseCase: GetDisciplinesUseCase
): ViewModel() {

    private val _user_loaded = mutableStateOf<HomeStates>(HomeStates.EmptyStart)
    val user_loaded: State<HomeStates> = _user_loaded

    fun onEvent(user: User){
        viewModelScope.launch(Dispatchers.IO) {

             disciplineUseCase.invoke(user).onSuccess { disciplines ->
                 Log.d("SUPABASE_DB_LOGS", "HomeViewModele discipline: $disciplines")

                 withContext(Dispatchers.Main) {
                     _user_loaded.value = HomeStates.HomeSuccessState(
                         user = user,
                         disciplines= disciplines
                     )
                 }

             }.onFailure { error ->
                 Log.d("SUPABASE_DB_LOGS", "HomeViewModele discipline: ${error.message}")
             }


        }
    }
}