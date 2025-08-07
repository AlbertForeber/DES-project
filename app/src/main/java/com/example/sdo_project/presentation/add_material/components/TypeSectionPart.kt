package com.example.sdo_project.presentation.add_material.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.example.sdo_project.domain.models.Discipline
import com.example.sdo_project.domain.models.MaterialSection
import com.example.sdo_project.presentation.common.CommonTextField
import com.example.sdo_project.presentation.common.DesButton

@Composable
fun TypeSectionPart (
    initialParent: MaterialSection?,
    discipline: Discipline?,
    addSection: (Discipline?, String, MaterialSection?) -> Unit
){
    var nameAddingItem by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        CommonTextField(
            text = nameAddingItem,
            onChangeText = {nameAddingItem = it},
            placeholder = "название"
        )

        DesButton(
            inText = "Добавить",
            onClick = {addSection(discipline, nameAddingItem, initialParent )}
        )
    }
}