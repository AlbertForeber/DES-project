package com.example.sdo_project.presentation.discipline

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.sdo_project.domain.models.Discipline
import com.example.sdo_project.presentation.MainState

@Composable
fun DisciplineScreen(
    mainState: MainState,
    navigateToGrade : () -> Unit,
    discipline: Discipline,
    navigateToParticipants: () -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {

        Text("Discipline")

        Button(
            onClick = { navigateToGrade() }
        ) {
            Text(text = "go to grade")
        }

        Button(
            onClick = { navigateToParticipants() }
        ) {
            Text(text = "go to participants")
        }
    }

}