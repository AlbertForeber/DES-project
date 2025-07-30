package com.example.sdo_project.presentation.teacher_grade_screen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.example.sdo_project.domain.models.GradeTeacherPoint
import com.example.sdo_project.domain.models.GradeWithStudentInfo
import com.example.sdo_project.presentation.teacher_grade_screen.TeacherEvent
import com.example.sdo_project.presentation.teacher_grade_screen.components.change_grade.ChangeGradeDialog
import com.example.sdo_project.presentation.teacher_grade_screen.components.grade_list.GradeListState

@Composable
fun PointAndGradesCard(
    pointToGrades: Pair<GradeTeacherPoint, GradeListState>,
    event: ( TeacherEvent ) -> Unit,
    groupId: Int,
    modifier: Modifier = Modifier
) {


    var expanded by remember { mutableStateOf(false) }


    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        // Отдельный компонент
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .height(30.dp)
        ) {
            Text(pointToGrades.first.name, style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold))
            Icon(Icons.Default.ArrowDropDown, null, modifier = Modifier.clickable {
                event(
                    TeacherEvent.GetGrades(
                        groupId = groupId,
                        pointId = pointToGrades.first.id
                    )
                )
                if ( expanded || pointToGrades.second is GradeListState.Loading || pointToGrades.second is GradeListState.Error )
                expanded = !expanded
            })
            //
        }
        HorizontalDivider(color = Color.LightGray )

        AnimatedVisibility(
            expanded
        ) {
            when (pointToGrades.second) {
                is GradeListState.Error -> {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                    ) {
                        Text("Error: ${(pointToGrades.second as GradeListState.Error).errorMessage}")
                    }
                }
                is GradeListState.Idle -> {
                    Column {
                        (pointToGrades.second as GradeListState.Idle).grades.forEach {
                            GradeElement(it, pointToGrades.first)
                        }
                    }
                }

                GradeListState.Loading -> {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }

    }
}

@Composable
fun GradeElement(info: GradeWithStudentInfo, point: GradeTeacherPoint) {
    var dialog by remember { mutableStateOf(false) }
    var gradeText by remember { mutableStateOf(info.currentScore.toString()) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
    ) {
        Text( "${info.surname} ${info.name} ${info.patronymic}", modifier = Modifier.padding(5.dp))
        GradePlaceButtonField(
            text = gradeText,
            onClick = {
                dialog = true
            }

        )
    }
    if ( dialog ) {
        ChangeGradeDialog(
            onDismissRequest = { dialog = false },
            onSuccess = { gradeText = it.toString() },
            maxGrade = point.maxScore,
            oldGrade = gradeText.toFloat(),
            pointId = point.id,
            uuid = info.uuid
        )
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewGradeElement() {
    GradeElement(
        info = GradeWithStudentInfo(
            uuid = "12341234",
            name = "Альберт",
            surname = "Шаймухаметов",
            patronymic = "Илдарович",
            currentScore = 19f
        ),
        GradeTeacherPoint(
            id = 1,
            name = "Kr",
            maxScore = 20f
        )
    )

}

@Preview(showBackground = true)
@Composable
fun PreviewPointAndGrades() {
    PointAndGradesCard(
        pointToGrades = GradeTeacherPoint(
            id = 1,
            name = "Контрольная работа №1",
            maxScore = 20f
        ) to GradeListState.Idle(
            grades = listOf(GradeWithStudentInfo(
                uuid = "1231231",
                name = "Альберт",
                surname = "Шаймухаметов",
                patronymic = "Илдарович",
                currentScore = 20f
            ))
        ),
        event = { },
        groupId = 1
    )
}