package com.example.sdo_project.presentation.material_section.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType

@Composable
fun BackStackText(
    sectionName: String,
    onClick: () -> Unit
) {
    Text(
        "${sectionName.trim()}/",
        modifier = Modifier.clickable { onClick() },
        lineHeight = TextUnit(15f, type = TextUnitType.Sp)
    )
}