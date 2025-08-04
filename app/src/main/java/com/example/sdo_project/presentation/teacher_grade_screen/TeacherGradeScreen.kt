package com.example.sdo_project.presentation.teacher_grade_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sdo_project.domain.models.GradeTeacherPoint
import com.example.sdo_project.domain.models.Group
import com.example.sdo_project.domain.models.User
import com.example.sdo_project.presentation.MainState
import com.example.sdo_project.presentation.common.LoadingElement
import com.example.sdo_project.presentation.common.error_screen.ErrorScreen
import com.example.sdo_project.presentation.common.error_screen.SizeParameter
import com.example.sdo_project.presentation.teacher_grade_screen.components.GradeDropDown
import com.example.sdo_project.presentation.teacher_grade_screen.components.GradeTextField
import com.example.sdo_project.presentation.teacher_grade_screen.components.PointAndGradesCard
import com.example.sdo_project.presentation.teacher_grade_screen.components.grade_list.GradeListState

@Composable
fun TeacherGradeScreen(
    event: ( TeacherEvent ) -> Unit,
    disciplineId: Int,
    mainState: MainState,
    teacherGradeState: TeacherGradeState,
    pointListState: Map<GradeTeacherPoint, GradeListState>
) {
    LaunchedEffect(Unit) {
        retryEvent(event, mainState, disciplineId)
    }
    //
    var dropDownExpanded by remember { mutableStateOf(false) }
    var chosenGroup by remember { mutableStateOf<Group?>(null) }
    val chosenGroupText by remember { derivedStateOf { chosenGroup?.name ?: "" } }
    //
    when (teacherGradeState) {
        is TeacherGradeState.Error -> {
            ErrorScreen(
                error = teacherGradeState.errorMessage,
                sizeParameter = SizeParameter.LargeSize,
                retryButtonEnabled = true,
                retryButton = {
                    retryEvent(event, mainState, disciplineId)
                }
            )
        }
        is TeacherGradeState.Idle -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    Box(
                        contentAlignment = Alignment.TopEnd,
                        modifier = Modifier.padding(5.dp)
                    ) {
                        GradeTextField(
                            chosenGroupText,
                            onClick = { dropDownExpanded = true },
                            placeholder = "Choose group"
                        )
                        GradeDropDown(
                            groups = teacherGradeState.availableGroups,
                            expanded = dropDownExpanded,
                            onDismissRequest = {
                                dropDownExpanded = false
                            },
                            onClick = { chosenGroup = it }
                        )
                    }
                }

                if ( chosenGroup != null ) {
                    pointListState.forEach {
                        item {
                            PointAndGradesCard(
                                it.toPair(),
                                event = event,
                                groupId = 1,
                                modifier = Modifier.animateItem()
                            )
                        }
                    }
                }

            }
        }
        TeacherGradeState.Loading -> {
            LoadingElement(modifier = Modifier.fillMaxSize())
        }
    }
}

private fun retryEvent( event: ( TeacherEvent ) -> Unit, mainState: MainState, disciplineId: Int,) {
    event(
        TeacherEvent.GetGroupsAndPoints(
            teacherUuid = (mainState as MainState.Authorized).user.uuid,
            disciplineId = disciplineId
        )
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewTeacherGradeScreen() {
    TeacherGradeScreen(
        {},
        2,
        MainState.Authorized(
            User(
                uuid = "830fac75-cb1b-426d-8f5d-c5780edde532",
                isTeacher = true,
                personalCode = "fashfash",
                surname = "1234",
                name = "12342",
                patronymic = "1234213",
                departmentId = 1,
                country = "12341234",
                city = "12341234",
            )
        ),
        TeacherGradeState.Idle(
            availableGroups = listOf(Group(
                id = 1,
                name = "ИКБО-41-24"
            ))
        ),
        mutableMapOf(GradeTeacherPoint(
            id = 1,
            name = "КР1",
            maxScore = 20f
        ) to GradeListState.Loading)
    )
}


