package com.example.sdo_project.presentation.student_grade

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sdo_project.domain.models.Discipline
import com.example.sdo_project.domain.models.GradePoint
import com.example.sdo_project.domain.models.GradeSection
import com.example.sdo_project.domain.models.User
import com.example.sdo_project.presentation.MainState
import com.example.sdo_project.presentation.student_grade.components.GradeSectionDetailedPart
import com.example.sdo_project.presentation.student_grade.components.GradeSectionPart
import com.example.sdo_project.presentation.student_grade.components.GradeSectionPartShimmer

@Composable
fun StudentGradeScreen(
    modifier: Modifier = Modifier,
    discipline: Discipline,
    onLoading: (Int, User)-> Unit,
    onEvent: (GradeSection, User) -> Unit,
    state: StudentGradeState,
    pointsGrades: Map<Int, List<GradePoint>?>,
    mainState: MainState
) {

    LaunchedEffect (Unit){
        if (mainState is MainState.Authorized){
            onLoading( discipline.id, mainState.user )
        }

    }

    Column (
        modifier =  modifier
            .padding(PaddingValues(start = 10.dp, top = 10.dp, end = 10.dp))
    ){
        Text(text = discipline.name)

        Spacer(modifier = Modifier.height(5.dp).background(Color.Transparent).fillMaxWidth(), ) // for constraints SpacerHeight
        Spacer(modifier = Modifier.height(1.dp).background(Color.Black).fillMaxWidth(), )
        Spacer(modifier = Modifier.height(10.dp).background(Color.Transparent).fillMaxWidth(), ) // for constraints SpacerHeight

        //non-detail part
        if (state is StudentGradeState.Success){
            GradeSectionPart(
                sectionsWithGrade = state.sectionGradeList
            )

            Spacer(modifier = Modifier.height(5.dp).background(Color.Transparent).fillMaxWidth(), ) // for constraints SpacerHeight
            Spacer(modifier = Modifier.height(1.dp).background(Color.Black).fillMaxWidth(), )
            Spacer(modifier = Modifier.height(10.dp).background(Color.Transparent).fillMaxWidth(), ) // for constraints SpacerHeight


            GradeSectionDetailedPart(
                pointsGrades = pointsGrades,
                sectionsWithGrade = state.sectionGradeList,
                onClick = onEvent,
                mainState = mainState
            )

        }
        else if (state is StudentGradeState.Loading){
            GradeSectionPartShimmer()
        }




    }


}