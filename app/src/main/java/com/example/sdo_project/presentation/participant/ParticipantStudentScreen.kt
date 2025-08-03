package com.example.sdo_project.presentation.participant

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sdo_project.domain.models.Group
import com.example.sdo_project.domain.models.User

@Composable
fun ParticipantStudentScreen (
    user: User,
    participants: List<User>,
    getStudentsByGroupId: (Int) -> Unit,
    group: Group?
){

    LaunchedEffect(Unit) {

        getStudentsByGroupId(user.departmentId)
    }

    LazyRow {
        item {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {

                item {
                    ParticipantHeaderElement()
                }

                items (participants.size){ index ->
                    val participant = participants[index]
                    ParticipantElement(
                        participant = participant,
                        group = group
                    )
                }
                if (participants.size >0 ){
                    items(100){ index ->
                        val participant = participants[index%2]
                        ParticipantElement(
                            participant = participant,
                            group = group
                        )
                    }
                }

            }
        }

    }

}