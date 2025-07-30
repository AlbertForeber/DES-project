package com.example.sdo_project.presentation.participant

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sdo_project.domain.models.Discipline
import com.example.sdo_project.domain.models.Group
import com.example.sdo_project.domain.models.User

@Composable
fun ParticipantScreen(
    user: User,
    getStudentsByGroupId: (Int) -> Unit,
    getGroupsByTeacherUuid: (String, Int) -> Unit,
    participants: List<User>,
    group: Group?,
    groups: ParticipantState,
    discipline: Discipline
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
                .padding(10.dp)
        ) {

            Text(text = "Участники")

            when (user.isTeacher){
                true -> {
                    ParticipantTeacherScreen(
                        user = user,
                        getGroupsByTeacherUuid = getGroupsByTeacherUuid,
                        participants = participants,
                        group = group,
                        groups = groups,
                        getStudentsByGroupId = getStudentsByGroupId,
                        discipline = discipline
                    )
                }
                false -> {
                    ParticipantStudentScreen(
                        participants = participants,
                        getStudentsByGroupId = getStudentsByGroupId,
                        user = user,
                        group = group
                    )
                }
            }

        }

}