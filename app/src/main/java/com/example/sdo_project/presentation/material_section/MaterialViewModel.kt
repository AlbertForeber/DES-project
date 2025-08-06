package com.example.sdo_project.presentation.material_section

import android.util.Log
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sdo_project.domain.models.MaterialSection
import com.example.sdo_project.domain.usecase.material.GetMaterialSectionsUseCase
import com.example.sdo_project.domain.usecase.material.GetMaterialsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class MaterialViewModel @Inject constructor(
    private val getMaterialSectionsUseCase: GetMaterialSectionsUseCase,
    private val getMaterialsUseCase: GetMaterialsUseCase
) : ViewModel() {

    private val _backStack = mutableStateListOf<MaterialSection>()
    val backStack: List<MaterialSection> = _backStack
    private var currentParent = _backStack.getOrNull(_backStack.lastIndex)
    private var _state = MutableStateFlow<MaterialSectionState>(MaterialSectionState.Loading)
    val state = _state.asStateFlow()



    fun onEvent( event: MaterialSectionEvent ) {
        viewModelScope.launch(Dispatchers.IO) {
            when(event) {
                is MaterialSectionEvent.Next -> {
                    _backStack.add(event.parent)
                    getMaterialSections(event.parent)
                }
                is MaterialSectionEvent.BackTo -> {
                    _backStack.removeRange(
                        _backStack.indexOf(event.section) + 1,
                        _backStack.lastIndex + 1
                    )
                    _backStack.lastOrNull()?.let { getMaterialSections(it) }
                }

                is MaterialSectionEvent.Update -> {
                    if (event.parent != null) {
                        _backStack.add(event.parent)
                        getMaterialSections(event.parent)
                    }
                    else
                        _backStack.lastOrNull()?.let { getMaterialSections(it) }
                }
            }
        }

    }

    private suspend fun getMaterialSections(parent: MaterialSection) {
        withContext(Dispatchers.Main) {
            _state.value = MaterialSectionState.Loading
        }
        getMaterialSectionsUseCase(parent).fold(
            onSuccess = {
                getMaterials(parent, it)
            },
            onFailure = {
                withContext(Dispatchers.Main) {
                    _state.value = MaterialSectionState.Error(it.message ?: "Unknown error")
                }
            }
        )
    }

    private suspend fun getMaterials(parent: MaterialSection, sections: List<MaterialSection>) {
        getMaterialsUseCase(parent).fold(
            onSuccess = {
                withContext(Dispatchers.Main) {
                    _state.value = MaterialSectionState.Idle(
                        availableSections = sections,
                        availableMaterials = it,
                    )
                }
            },
            onFailure = {
                withContext(Dispatchers.Main) {
                    _state.value = MaterialSectionState.Error(it.message ?: "Unknown error")
                }
            }
        )
    }
}