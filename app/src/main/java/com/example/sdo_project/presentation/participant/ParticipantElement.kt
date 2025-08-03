package com.example.sdo_project.presentation.participant

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.sdo_project.domain.models.Group
import com.example.sdo_project.domain.models.User

@Composable
fun ParticipantElement(
    participant: User,
    group: Group?
) {

    Row (

        modifier = Modifier.wrapContentSize(),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {

            Box(
                modifier = Modifier.width(140.dp)
            ){
                Text(text = "${participant.name} ${participant.patronymic} ${participant.surname}",
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3)
            }


            Box(modifier = Modifier.width(70.dp)){
                Text(text = participant.personalCode)
            }


            Box(
                modifier = Modifier.width(70.dp)
            ){
                Text(text = "Студент")
            }


            Box(
                modifier = Modifier.width(120.dp)
            ){
                Text(text = group?.name ?: "-")
            }


    }
}