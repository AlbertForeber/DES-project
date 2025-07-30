package com.example.sdo_project.presentation.auth.components

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

@Composable
fun AuthTextField(
    text: String,
    onChangeText: (String) -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = LocalTextStyle.current,
    placeholder: String = ""
) {
    OutlinedTextField(
        text,
        onChangeText,
        textStyle = textStyle,
        modifier = modifier,
        placeholder = { Text(placeholder) }
    )
}