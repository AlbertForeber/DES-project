package com.example.sdo_project.presentation.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun ProfileFieldWithTitle(
    text: String,
    onChange: ( String ) -> Unit,
    title: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = LocalTextStyle.current,
    placeholder: String = "",
    editable: Boolean = true,
    active: Boolean = true
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(title, style = MaterialTheme.typography.titleLarge)
        OutlinedTextField(
            text,
            onChange,
            textStyle = textStyle,
            modifier = modifier,
            placeholder = { Text(placeholder) },
            singleLine = true,
            readOnly = !editable,
            enabled = active
        )
    }

}