package com.example.sdo_project.presentation.student_grade.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sdo_project.domain.models.GradePoint


@Composable
fun GradePointList (
    points: List<GradePoint>
){

    LazyColumn(
        modifier = Modifier
            .heightIn(max = 200.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        items(points.size){point ->

            val curPoint = points[point]
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(text = curPoint.name)

                Text(text = "${curPoint.currentScore} / ${curPoint.maxScore}")
            }


        }


    }
}