package com.example.sdo_project.presentation.teacher_grade_screen.components.change_grade

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.sdo_project.presentation.common.DesButton
import com.example.sdo_project.presentation.common.error_screen.ErrorScreen
import com.example.sdo_project.presentation.common.error_screen.SizeParameter

@Composable
fun ChangeGradeDialog(
    onDismissRequest: () -> Unit,
    onSuccess: ( Float ) -> Unit,
    maxGrade: Float,
    oldGrade: Float,
    pointId: Int,
    uuid: String,
    state: ChangeGradeState,
    event: ( ChangeGradeEvent ) -> Unit,
) {
    Dialog(
        onDismissRequest = {
            event(ChangeGradeEvent.BackToIdle)
            onDismissRequest()
        }
    ) {
        var newGrade by remember { mutableFloatStateOf(oldGrade) }

        Surface(
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.size(250.dp, 150.dp)
        ) {
            when(state) {
                is ChangeGradeState.Error -> {
                    ErrorScreen(
                        error = state.errorMessage,
                        sizeParameter = SizeParameter.SmallSize
                    )
                }
                ChangeGradeState.Idle -> {
                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Text( "Change grade", style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold) )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(5.dp)
                        ) {
                            GradePlaceEditableTextField(
                                newGrade.toString(),
                                { newGrade = it.toFloatOrNull() ?: newGrade }
                            )
                            Text("/${maxGrade}", style = MaterialTheme.typography.headlineSmall)
                            VerticalDivider(modifier = Modifier.padding(15.dp, 10.dp, 5.dp, 10.dp))
                            IconButton(
                                onClick = {
                                    event(
                                        ChangeGradeEvent.ChangeGrade(
                                            newGrade,
                                            pointId,
                                            uuid
                                        )
                                    )
                                },
                                enabled = newGrade <= maxGrade
                            ) {
                                Icon(Icons.Default.Done, null, tint = Color.Green)
                            }
                        }
                    }
                }
                ChangeGradeState.Loading -> {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.size(60.dp)
                    ) {
                        CircularProgressIndicator()
                    }
                }
                ChangeGradeState.Success -> {
                    onSuccess(newGrade)
                    event(ChangeGradeEvent.BackToIdle)
                    onDismissRequest()
                }
            }

        }


    }

}

@Suppress("UNCHECKED_CAST")
@Preview(showBackground = true)
@Composable
fun ChangeGradeDialogPreview( ) {
    ChangeGradeDialog(
        onDismissRequest = {},
        {},
        maxGrade = 20f,
        oldGrade = 19.8f,
        pointId = 1,
        uuid = "212341",
        state = ChangeGradeState.Idle,
        event = {  }
    )
}

@Suppress("UNCHECKED_CAST")
@Preview(showBackground = true)
@Composable
fun ChangeGradeDialogPreviewError( ) {
    val emptyfun = { a: Float, b: Int, c: String -> Unit }
    ChangeGradeDialog(
        onDismissRequest = {},
        {},
        maxGrade = 20f,
        oldGrade = 19.8f,
        pointId = 1,
        uuid = "212341",
        state = ChangeGradeState.Error("Time Out"),
        event = {  }
    )
}

