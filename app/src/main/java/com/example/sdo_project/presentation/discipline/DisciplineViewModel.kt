package com.example.sdo_project.presentation.discipline

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sdo_project.domain.models.Discipline
import com.example.sdo_project.domain.models.MaterialSection
import com.example.sdo_project.domain.usecase.material.GetMaterialSectionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DisciplineViewModel @Inject constructor(
        private val getMaterialSectionsUseCase: GetMaterialSectionsUseCase
): ViewModel() {

    private val _state = mutableStateOf<DisciplineState>(DisciplineState.Start)
    val state: State<DisciplineState> = _state


    // get material_sections
    fun onLoading(discipline: Discipline){
        _state.value = DisciplineState.Loading
        viewModelScope.launch(Dispatchers.IO) {

            getMaterialSectionsUseCase(disciplineId = discipline.id).onSuccess { list ->
                withContext(Dispatchers.Main){
                    _state.value = DisciplineState.Success(materialSections = list)
                }
            }
            .onFailure { error ->
                _state.value = DisciplineState.Error(message = error.message ?: "Unknown error")
                Log.d("SUPABASE_DB_LOGS", "DisciplineViewModel list onLoading: ${error.message}")

            }
        }

    }
}