package com.example.sdo_project.presentation.student_grade.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sdo_project.domain.models.GradeSection
import com.example.sdo_project.presentation.home.components.shimmerEffect

@Composable
fun GradeSectionPart(
    sectionsWithGrade: List<GradeSection>,
){

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) { items (sectionsWithGrade.size) { section ->
            val curSection = sectionsWithGrade[section]

        Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.height(50.dp).fillMaxWidth().padding(10.dp)
            ) {
                Text(text= curSection.name)
                Text(text = "${curSection.currentScore} / ${curSection.maxScore}")
            }
        }
    }

}

@Composable
fun GradeSectionPartShimmer (){

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item(4) {
            Row(
                modifier = Modifier.height(20.dp)
                    .fillMaxWidth()
                    .padding(10.dp)
                    .shimmerEffect()
            ) {  }
        }
    }

}