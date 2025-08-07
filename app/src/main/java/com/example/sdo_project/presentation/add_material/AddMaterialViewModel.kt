package com.example.sdo_project.presentation.add_material

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sdo_project.data.remote.dto.NewMaterialDto
import com.example.sdo_project.domain.models.Discipline
import com.example.sdo_project.domain.models.Material
import com.example.sdo_project.domain.models.MaterialSection
import com.example.sdo_project.domain.usecase.material.AddMaterialSectionUseCase
import com.example.sdo_project.domain.usecase.material.AddMaterialSectionsUseCase
import com.example.sdo_project.domain.usecase.material.AddMaterialUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class AddMaterialViewModel @Inject constructor(
    private val addMaterialUseCase: AddMaterialUseCase,
    private val addMaterialSectionUseCase: AddMaterialSectionUseCase,
    private val addMaterialSectionsUseCase: AddMaterialSectionsUseCase
):ViewModel() {

    private val _state = mutableStateOf<AddMaterialState>(AddMaterialState.Start)
    val state : State<AddMaterialState> = _state


    fun addMaterial(initialParent: MaterialSection,name: String, content: String, accessTime: String){
        _state.value = AddMaterialState.Loading
        viewModelScope.launch (Dispatchers.IO){
            val material =Material(
                name = name,
                content = content,
                accessTime = accessTime,
                sectionId = initialParent.id
            )
            addMaterialUseCase(material =material ).onSuccess { result ->
                withContext(Dispatchers.Main){
                    _state.value = AddMaterialState.Success
                }

            }
                .onFailure { error ->
                    withContext(Dispatchers.Main){
                        _state.value = AddMaterialState.Error(message = error.message ?: "Unknown error")
                    }
                    Log.d("SUPABASE_DB_LOGS", "AddMaterialViewModel addMaterial : ${error.message}")

                }
        }
    }

    // если заходят с экрана секции, то оттуда будет браться disciplineId
    fun addSection(discipline: Discipline?,name: String, initialParent: MaterialSection?){
        // throw error if disciplinee and initalParent null
        _state.value = AddMaterialState.Loading
        val newSection = MaterialSection(
            name = name,
            parentId = if (initialParent != null) initialParent.id else  null,
            disciplineId = if (discipline != null) discipline.id else initialParent!!.disciplineId
        )

        viewModelScope.launch (Dispatchers.IO){
            addMaterialSectionUseCase(section = newSection).onSuccess { result ->

                withContext(Dispatchers.Main){
                    _state.value = AddMaterialState.Success
                }

            }.onFailure { error ->
                withContext(Dispatchers.Main){
                    _state.value = AddMaterialState.Error(message = error.message ?: "Unknown error")
                }
                Log.d("SUPABASE_DB_LOGS", "AddMaterialViewModel addSection : ${error.message}")

            }
        }
    }

    fun addSections(discipline: Discipline?, names:  List<String>, initialParent: MaterialSection?){

        val disciplineId_ = discipline?.id ?: initialParent!!.disciplineId

        val newSection = mutableListOf(
            MaterialSection (
                name = names[0],
                parentId = initialParent?.id,
                disciplineId = disciplineId_
            )
        )

        for (index in 1..<names.size){
            newSection.add(
                MaterialSection (
                    name = names[index],
                    parentId = null,
                    disciplineId = disciplineId_
                )
            )
        }

        _state.value = AddMaterialState.Loading
        viewModelScope.launch (Dispatchers.IO){
            addMaterialSectionsUseCase(sections = newSection, initialParent = initialParent?.id)
                .onSuccess { res ->

                    withContext(Dispatchers.Main){
                        _state.value = AddMaterialState.Success
                    }

                }
                .onFailure { error ->
                    withContext(Dispatchers.Main){
                        _state.value = AddMaterialState.Error(message = error.message ?: "Unknown error")
                    }
                    Log.d("SUPABASE_DB_LOGS", "AddMaterialViewModel addSections : ${error.message}")

                }
        }

    }


}