package com.example.sdo_project.presentation.participant

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sdo_project.domain.models.Discipline
import com.example.sdo_project.domain.models.Group
import com.example.sdo_project.domain.models.User
import kotlin.math.exp

@Composable
fun ParticipantTeacherScreen(
    getGroupsByTeacherUuid: (String, Int) -> Unit,
    user: User,
    participants: List<User>,
    group: Group?,
    groups: ParticipantState,
    getStudentsByGroupId: (Int) -> Unit,
    discipline: Discipline
) {

    var isExpanded by remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {
        getGroupsByTeacherUuid(user.uuid, discipline.id)
    }


    if (groups is ParticipantState.Success){


        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Box(
                modifier = Modifier.wrapContentSize(Alignment.TopStart)
            ){
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .background(Color.Gray)
                        .clickable {
                            isExpanded = !isExpanded
                        }
                ) {
                    Text(text =  if (isExpanded) "Выберите группу" else "")
                }
                DropdownMenu(
                    modifier = Modifier.fillMaxWidth().heightIn(min = 50.dp),
                    expanded = isExpanded,
                    onDismissRequest = {isExpanded = !isExpanded}
                ) {
                    groups.groups.forEach { _group ->
                        DropdownMenuItem(
                            text = { Text(text = _group.name, color = Color.White) },
                            onClick = {getStudentsByGroupId(_group.id)}
                        )
                    }
                }
            }


            Spacer(modifier = Modifier
                .height(5.dp)
                .background(Color.Transparent)
                .fillMaxWidth(), ) // for constraints SpacerHeight
            Spacer(modifier = Modifier
                .height(1.dp)
                .background(Color.Black)
                .fillMaxWidth(), )
            Spacer(modifier = Modifier
                .height(10.dp)
                .background(Color.Transparent)
                .fillMaxWidth(), ) // for constraints SpacerHeight


            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items (participants.size){ index ->
                    val participant = participants[index]

                    ParticipantElement(
                        participant = participant,
                        group = group
                    )
                }
            }
        }

    }


}