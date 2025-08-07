package com.example.sdo_project.presentation.add_material.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sdo_project.domain.models.MaterialSection
import com.example.sdo_project.presentation.common.CommonTextField
import com.example.sdo_project.presentation.common.DesButton
import java.time.LocalTime

@Composable
fun TypeMaterialPart(
    initialParent: MaterialSection,
    addMaterial: (MaterialSection, String, String, String) -> Unit,
) {

    var nameAddingItem by remember { mutableStateOf("") }
    var linkAddingMaterial by remember { mutableStateOf("") }
    var accessTime by remember { mutableStateOf(LocalTime.now().toString()) }


    LazyColumn(
        modifier = Modifier.fillMaxWidth().padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        item {
            CommonTextField(
                modifier = Modifier.fillMaxWidth(),
                text = nameAddingItem,
                onChangeText = {nameAddingItem = it},
                placeholder = "название"
            )
        }

        item{
            CommonTextField(
                modifier = Modifier.fillMaxWidth(),
                text = linkAddingMaterial,
                onChangeText = {linkAddingMaterial = it},
                placeholder = "ссылка"
            )
        }

        item{
            DateTimePicker(
                setDateTime = {newValue ->
                    accessTime = newValue
                }
            )
        }

        item{
            DesButton(
                inText = "Добавить",
                onClick = {
                    addMaterial(initialParent, nameAddingItem, linkAddingMaterial, accessTime)
                    nameAddingItem = ""
                    linkAddingMaterial = ""}
            )
        }

    }


}