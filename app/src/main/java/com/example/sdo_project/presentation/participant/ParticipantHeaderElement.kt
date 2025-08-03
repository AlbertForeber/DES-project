package com.example.sdo_project.presentation.participant

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun ParticipantHeaderElement(){

    Row (

        modifier = Modifier.wrapContentSize().padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        Box(
            modifier = Modifier.width(140.dp)
        ){
            Text(text = "ФИО",
                overflow = TextOverflow.Ellipsis,
                maxLines = 3)
        }


        Box(modifier = Modifier.width(70.dp)){
            Text(text = "Код")
        }


        Box(
            modifier = Modifier.width(70.dp)
        ){
            Text(text = "Роль")
        }


        Box(
            modifier = Modifier.width(120.dp)
        ){
            Text(text = "Название Группы")
        }


    }

}