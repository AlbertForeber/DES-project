package com.example.sdo_project.presentation.participant

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.sdo_project.domain.models.Discipline
import com.example.sdo_project.domain.models.Group
import com.example.sdo_project.domain.models.User
import com.example.sdo_project.presentation.common.LoadingElement

@Composable
fun ParticipantScreen(
    user: User,
    getStudentsByGroupId: (Int) -> Unit,
    getGroupsByTeacherUuid: (String, Int) -> Unit,
    //participants: List<User>,
    participantsState: ParticipantStudentListState,
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
                                participantsState = participantsState,
                                group = group,
                                groups = groups,
                                getStudentsByGroupId = getStudentsByGroupId,
                                discipline = discipline
                            )
                        }
                        false -> {
                            ParticipantStudentScreen(
                                participantsState = participantsState,
                                getStudentsByGroupId = getStudentsByGroupId,
                                user = user,
                                group = group
                            )
                        }
                    }




        }


}

