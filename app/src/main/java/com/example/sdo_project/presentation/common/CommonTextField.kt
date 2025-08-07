package com.example.sdo_project.presentation.common

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun CommonTextField(
    text: String,
    onChangeText: (String) -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = LocalTextStyle.current,
    placeholder: String = "",
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    readOnly :Boolean = false
) {
    OutlinedTextField(
        value = text,
        onValueChange = onChangeText,
        textStyle = textStyle,
        modifier = modifier,
        placeholder = { Text(placeholder) },
        visualTransformation = visualTransformation,
        keyboardOptions =  keyboardOptions,
        readOnly = readOnly
    )
}