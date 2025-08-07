package com.example.sdo_project.presentation.discipline

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sdo_project.domain.models.Discipline
import com.example.sdo_project.domain.models.MaterialSection
import com.example.sdo_project.presentation.MainState
import com.example.sdo_project.presentation.common.DesButton
import com.example.sdo_project.presentation.common.LoadingElement

@Composable
fun DisciplineScreen(
    mainState: MainState,
    navigateToGrade : () -> Unit,
    discipline: Discipline,
    navigateToParticipants: () -> Unit,
    state: DisciplineState,
    onSectionClick: (MaterialSection) -> Unit,
    onLoading: (Discipline) -> Unit,
    navigateToAddMaterial: () -> Unit
) {

    LaunchedEffect(Unit) {
        onLoading(discipline)
    }


    val checkedState = handleDisciplineState(state)

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(horizontal = 10.dp),

    ) {

        item{
            Text(text = discipline.name)
        }

        item {

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
        }

        item {
            Spacer(modifier = Modifier.height(5.dp).background(Color.Transparent).fillMaxWidth(), ) // for constraints SpacerHeight
            Spacer(modifier = Modifier.height(1.dp).background(Color.Black).fillMaxWidth(), )
            Spacer(modifier = Modifier.height(10.dp).background(Color.Transparent).fillMaxWidth(), ) // for constraints SpacerHeight

        }


        item {
            Text(text = "Общие Сведения")
        }

        item {
            Text(
                text = "Кафедра: ${discipline.departmentName}\nИнститут: ${discipline.instituteName}"
            )
        }

        item {
            Spacer(modifier = Modifier.height(5.dp).background(Color.Transparent).fillMaxWidth(), ) // for constraints SpacerHeight
            Spacer(modifier = Modifier.height(1.dp).background(Color.Black).fillMaxWidth(), )
            Spacer(modifier = Modifier.height(10.dp).background(Color.Transparent).fillMaxWidth(), ) // for constraints SpacerHeight

        }







        item {
            if ( checkedState){

                val state_ = state as DisciplineState.Success
                MaterialSectionsList(
                    sections = state_.materialSections,
                    onClick = onSectionClick
                )

            }else {
                HandleNonSuccessState(state = state)
            }
        }

        item {
            if ((mainState as MainState.Authorized).user.isTeacher){
                DesButton(
                    inText = "Добавить материал",
                    onClick = {navigateToAddMaterial()}
                )
            }
        }



    }

}



private fun handleDisciplineState (state: DisciplineState,
): Boolean {


    return when(state){
        is DisciplineState.Loading -> {
            false
        }
        is DisciplineState.Success -> {
            true
        }
        is DisciplineState.Start -> {
            false
        }
        is DisciplineState.Error -> {
            false
        }
    }
}

@Composable
private fun HandleNonSuccessState (
    state: DisciplineState
){
    if (state is DisciplineState.Start || state is DisciplineState.Loading){
        LoadingElement()
    }
    else {
        Text(text = "Попробуйте чуть позже")
    }
}