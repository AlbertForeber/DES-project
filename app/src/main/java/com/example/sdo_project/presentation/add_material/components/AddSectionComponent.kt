package com.example.sdo_project.presentation.add_material.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.sdo_project.R
import com.example.sdo_project.presentation.common.CommonTextField

@Composable
fun  AddSectionComponent(
    notFirst: Boolean = true,
    addNewSection: (String)-> Unit,
    removeSection:(String) -> Unit
) {

    var nameAddingItem by remember { mutableStateOf("") }
    var isAdded by remember { mutableStateOf(false) }
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        CommonTextField(
            text = nameAddingItem,
            onChangeText = {nameAddingItem = it},
            placeholder = "название"
        )

        Button(
            onClick = {
                if (!isAdded && nameAddingItem.isNotBlank()){
                    addNewSection (nameAddingItem)
                    isAdded = true
                }
                 },
            modifier = Modifier.size(30.26.dp)
        ) {
            Icon(painter = painterResource(R.drawable.add_icon), contentDescription = "plus")
        }

        if (notFirst){

            Button(
                onClick = {removeSection(nameAddingItem)
                          isAdded = false},
                modifier = Modifier.size(30.26.dp)
            ) {
                Icon(painter = painterResource(R.drawable.remove_icon), contentDescription = "plus")
            }
        }


    }
}