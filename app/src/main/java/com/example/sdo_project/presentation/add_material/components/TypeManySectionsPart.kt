package com.example.sdo_project.presentation.add_material.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.example.sdo_project.domain.models.Discipline
import com.example.sdo_project.domain.models.MaterialSection
import com.example.sdo_project.presentation.common.CommonTextField

@Composable
fun TypeManySectionsPart (
    initialParent : MaterialSection?,
    discipline: Discipline?,
    addSections: (Discipline?, List<String>, MaterialSection?) -> Unit
){


    val sectionsNames = remember { mutableStateOf(emptyList<String>()) }
    var count by remember { mutableIntStateOf(0) }


    val newPath = remember (sectionsNames.value){sectionsNames.value.joinToString("/") }

    fun removeSection (name: String){
            sectionsNames.value = sectionsNames.value.filter { value ->
                value != name
            }
        count -=1;
    }

    fun addSection (name: String){
        sectionsNames.value += name
        count+=1;
    }

    LazyColumn (
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item{
            AddSectionComponent(
                notFirst = false,
                addNewSection = {name ->  addSection(name) },
                removeSection = { name -> removeSection(name) }
            )
        }

        items( count =  sectionsNames.value.size ){
            Log.d("KATRIN_BE", "${sectionsNames.value.size}")
            AddSectionComponent(
                addNewSection = {name ->  addSection(name) },
                removeSection = { name -> removeSection(name) }
            )
        }

        item{
            CommonTextField(
                text = newPath,
                onChangeText = {},
                )
        }

        item {
            Button(
                onClick = {addSections(discipline,sectionsNames.value ,initialParent)}
            ) {
                Text("Добавить")
            }
        }



    }
}