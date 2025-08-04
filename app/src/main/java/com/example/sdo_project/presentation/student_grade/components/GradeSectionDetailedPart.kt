package com.example.sdo_project.presentation.student_grade.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.sdo_project.R
import com.example.sdo_project.domain.models.GradePoint
import com.example.sdo_project.domain.models.GradeSection
import com.example.sdo_project.domain.models.User
import com.example.sdo_project.presentation.MainState
import com.example.sdo_project.presentation.student_grade.StudentPointGradeState


@Composable
fun GradeSectionDetailedPart(
//    pointsGrades: Map<Int, List<GradePoint>?>,
    pointsGrades: StudentPointGradeState,
    sectionsWithGrade: List<GradeSection>,
    onClick: (GradeSection, User) -> Unit,
    mainState: MainState
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) { items (sectionsWithGrade.size) { section ->

        val curSection = sectionsWithGrade[section]
        GradeSectionDetailedComponent(
            section = curSection,
            pointsGrades = pointsGrades,
            onClick = onClick,
            mainState = mainState
        )


    }
    }

}