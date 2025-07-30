package com.example.sdo_project.presentation.common

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DesButton(
    inText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    active: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = active
    ) {
        Text(inText)
    }
}