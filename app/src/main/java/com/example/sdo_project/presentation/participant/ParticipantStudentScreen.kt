package com.example.sdo_project.presentation.participant

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sdo_project.domain.models.Group
import com.example.sdo_project.domain.models.User
import com.example.sdo_project.presentation.common.LoadingElement

@Composable
fun ParticipantStudentScreen (
    user: User,
    participantsState: ParticipantStudentListState,
    getStudentsByGroupId: (Int) -> Unit,
    group: Group?
){

    val checkedState = handleParticipantsState(participantsState)

    LaunchedEffect(Unit) {

        getStudentsByGroupId(user.departmentId)
    }

    when(checkedState){

        true -> {
            val participants = (participantsState as ParticipantStudentListState.Success).participants

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


                    }
                }

            }
        }

        false -> {
            HandleNonSuccessState(participantsState, { getStudentsByGroupId(user.departmentId) })
        }
    }



}

@Composable
private fun HandleNonSuccessState(
    state: ParticipantStudentListState,
    onError: ()-> Unit
){

    if (state is ParticipantStudentListState.Error){
        Button(onClick = onError) {
            Text(text= "Retry")
        }
    }
    else {
        LoadingElement()
    }

}


@Composable
private fun handleParticipantsState(state: ParticipantStudentListState,
): Boolean{
    //val context_ = LocalContext.current

    return when(state){
        is ParticipantStudentListState.Start -> {
            false
        }
        is ParticipantStudentListState.Success -> {
            true
        }
        is ParticipantStudentListState.Loading -> {

            false
        }
        is ParticipantStudentListState.Error -> {
            //Toast.makeText(context_, state.message, Toast.LENGTH_SHORT).show()
            false
        }
    }
}