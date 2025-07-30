package com.example.sdo_project.presentation.student_grade.components

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.sdo_project.R
import com.example.sdo_project.domain.models.GradePoint
import com.example.sdo_project.domain.models.GradeSection

@Composable
fun GradeSectionDetailedComponent (
    section: GradeSection,
    onClick: (GradeSection) -> Unit,
    pointsGrades: Map<Int, List<GradePoint>?>,
){

    var isDetailed by remember { mutableStateOf<Boolean>(false) }



    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        // main part - section name
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()

        ) {
            Text(text= section.name)

            IconButton(
                onClick = {
                    if ( pointsGrades[section.id] == null ) onClick(section)

                    isDetailed = !isDetailed
                }
            ) {
                Icon(painter = painterResource(R.drawable.ic_arrow_down) , contentDescription = "")
            }

        }


        //points list
        AnimatedVisibility(
            visible = isDetailed && (pointsGrades[section.id] != null),
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically(),
            modifier = Modifier.zIndex(1f)
        ) {

            GradePointList(
                points =  pointsGrades[section.id]!!
            )


        }
    }


}