package com.example.sdo_project.presentation.discipline

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sdo_project.domain.models.Discipline
import com.example.sdo_project.presentation.MainState

@Composable
fun DisciplineScreen(
    mainState: MainState,
    navigateToGrade : () -> Unit,
    discipline: Discipline,
    navigateToParticipants: () -> Unit,
    state: DisciplineState,
    onSectionClick: (Int, Int?) -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = discipline.name)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Button(
                onClick = { navigateToGrade() }
            ) {
                Text(text = "Баллы")
            }

            Button(
                onClick = { navigateToParticipants() }
            ) {
                Text(text = "Участниики")
            }
        }


        Spacer(modifier = Modifier.height(5.dp).background(Color.Transparent).fillMaxWidth(), ) // for constraints SpacerHeight
        Spacer(modifier = Modifier.height(1.dp).background(Color.Black).fillMaxWidth(), )
        Spacer(modifier = Modifier.height(10.dp).background(Color.Transparent).fillMaxWidth(), ) // for constraints SpacerHeight


        Text(text = "Общие Сведения")

        Text(
            text = "Кафедра: ${discipline.departmentName}\nИнститут: ${discipline.instituteName}"
        )


        Spacer(modifier = Modifier.height(5.dp).background(Color.Transparent).fillMaxWidth(), ) // for constraints SpacerHeight
        Spacer(modifier = Modifier.height(1.dp).background(Color.Black).fillMaxWidth(), )
        Spacer(modifier = Modifier.height(10.dp).background(Color.Transparent).fillMaxWidth(), ) // for constraints SpacerHeight


        when(state){
            is DisciplineState.Loading -> {}
            is DisciplineState.Success -> {
                MaterialSectionsList(
                    sections = state.materialSections,
                    discipline = discipline,
                    onClick = onSectionClick
                )
            }
            is DisciplineState.Start -> {

            }
        }

    }

}