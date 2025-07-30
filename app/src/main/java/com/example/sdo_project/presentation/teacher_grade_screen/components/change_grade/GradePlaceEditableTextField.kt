package com.example.sdo_project.presentation.teacher_grade_screen.components.change_grade

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun GradePlaceEditableTextField(
    text: String,
    onChange: ( String ) -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = LocalTextStyle.current,
    placeholder: String = ""
) {
    OutlinedTextField(
        text,
        onChange,
        textStyle = textStyle.copy(textAlign = TextAlign.Center),
        modifier = modifier
            .width(80.dp)
            .height(60.dp)
            .padding(5.dp),
        placeholder = { Text(placeholder) },
        singleLine = true,
    )
}