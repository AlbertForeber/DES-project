package com.example.sdo_project.presentation.teacher_grade_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun GradeTextField(
    text: String,
    onClick: ( ) -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = LocalTextStyle.current,
    placeholder: String = ""
) {
    OutlinedTextField(
        text,
        {},
        textStyle = textStyle,
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp),
        placeholder = { Text(placeholder) },
        singleLine = true,
        readOnly = true,
        trailingIcon = { Icon(Icons.Default.ArrowDropDown, null, modifier = Modifier.clickable { onClick() }) }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewGradeTextField() {
    GradeTextField(
        "",
        {},
        placeholder = "Choose group"
    )
}