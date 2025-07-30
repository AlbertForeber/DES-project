package com.example.sdo_project.presentation.teacher_grade_screen.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun GradePlaceButtonField(
    text: String,
    onClick: ( ) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .width(100.dp)
            .height(60.dp)
            .padding(5.dp),
        shape = MaterialTheme.shapes.extraSmall
    ) {
        Text(text)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGradeEditableTextField() {
    GradePlaceButtonField(
        "19.87",
        {}
    )
}