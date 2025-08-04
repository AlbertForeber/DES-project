package com.example.sdo_project.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sdo_project.domain.models.Discipline
import com.example.sdo_project.domain.models.User
import com.example.sdo_project.presentation.home.components.DisciplineListShimmer
import com.example.sdo_project.presentation.home.components.DisciplinesList
import com.example.sdo_project.presentation.home.components.shimmerEffect

@Composable
fun HomeScreen(

    onClick: (Discipline) -> Unit,
    pageState: HomeStates,
    event: () -> Unit,
    modifier: Modifier = Modifier
){

    LaunchedEffect(Unit) {
        event() // call for fetching user and disciplines in viewModel
    }

    // all information is fetched
    if  ( pageState is HomeStates.HomeSuccessState ){

        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Box(modifier = Modifier.height(40.dp)
                .padding(horizontal = 10.dp)
                .fillMaxWidth(),
                contentAlignment = Alignment.CenterStart
            ){
                Text(text = pageState.user.name)
            }

            Spacer(modifier = Modifier.height(5.dp).background(Color.Transparent).fillMaxWidth(), ) // for constraints SpacerHeight
            Spacer(modifier = Modifier.height(1.dp).background(Color.Black).fillMaxWidth(), )
            Spacer(modifier = Modifier.height(10.dp).background(Color.Transparent).fillMaxWidth(), ) // for constraints SpacerHeight

            DisciplinesList(
                disciplines = pageState.disciplines,
                onClick = onClick
            )

        }
    }

    else if (pageState is HomeStates.Error){
        Button(onClick = event) {
            Text(text = "Retry")
        }
    }
    else {
        HomeScreenShimmer(
            modifier = modifier
        )
    }



}

@Composable
fun HomeScreenShimmer(
    modifier: Modifier = Modifier
){

    Column (
        modifier = modifier
    ){

        Box(modifier = Modifier.height(40.dp).fillMaxWidth().padding(horizontal = 10.dp).shimmerEffect())

        Spacer(modifier = Modifier.height(5.dp).background(Color.Transparent), ) // for constraints SpacerHeight
        Spacer(modifier = Modifier.height(1.dp).background(Color.Black), )
        Spacer(modifier = Modifier.height(10.dp).background(Color.Transparent), ) // for constraints SpacerHeight

        DisciplineListShimmer()

    }
}