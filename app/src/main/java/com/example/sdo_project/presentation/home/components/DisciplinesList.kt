package com.example.sdo_project.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sdo_project.domain.models.Discipline

@Composable
fun DisciplinesList(
    disciplines: List<Discipline>,
    onClick: (Discipline) -> Unit
){
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(all = 5.dp)
    ) {
        items (disciplines.size){ index ->

            DisciplineCard(
                discipline = disciplines[index],
                onClick = onClick
            )
        }

    }
}

@Composable
fun DisciplineListShimmer (){
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        repeat(3){
            DisciplineCardShimmer()
        }
    }
}