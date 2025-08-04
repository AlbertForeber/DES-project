package com.example.sdo_project.presentation.material_section.components

import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MaterialsIconButton(
    icon: Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    active: Boolean = true
) {
    IconButton(
        onClick = onClick,
        modifier = modifier,
        enabled = active
    ) {
        icon
    }
}