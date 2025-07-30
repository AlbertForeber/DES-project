package com.example.sdo_project.presentation.teacher_grade_screen.components.change_grade

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sdo_project.presentation.common.DesButton

@Composable
fun ChangeGradeDialog(
    onDismissRequest: () -> Unit,
    onSuccess: ( Float ) -> Unit,
    maxGrade: Float,
    oldGrade: Float,
    viewModel: ChangeGradeViewModel = hiltViewModel(),
    pointId: Int,
    uuid: String
) {
    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        var newGrade by remember { mutableFloatStateOf(oldGrade) }
        val state = viewModel.state.collectAsState()

        Surface(
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.size(250.dp)
        ) {
            when(state.value) {
                is ChangeGradeState.Error -> {
                    Text("Error: ${(state.value as ChangeGradeState.Error).errorMessage}")
                }
                ChangeGradeState.Idle -> {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
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
                        }

                        DesButton(
                            inText = "Подтвердить",
                            onClick = {
                                viewModel.changeGrade(
                                    newGrade = newGrade,
                                    pointId = pointId,
                                    uuid = uuid
                                )
                            }
                        )
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
                    onDismissRequest()
                }
            }

        }


    }

}

@Preview(showBackground = true)
@Composable
fun ChangeGradeDialogPreview( ) {
    ChangeGradeDialog(
        onDismissRequest = {},
        {},
        maxGrade = 20f,
        oldGrade = 19.8f,
        pointId = 1,
        uuid = "212341"
    )
}