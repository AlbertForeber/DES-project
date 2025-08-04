package com.example.sdo_project.presentation.material_section.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BackStackText(
    sectionName: String,
    onClick: () -> Unit
) {
    Text(
        "$sectionName/",
        modifier = Modifier.clickable { onClick() }
    )
}