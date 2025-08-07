package com.example.sdo_project.presentation.discipline

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sdo_project.domain.models.Discipline
import com.example.sdo_project.domain.models.MaterialSection

@Composable
fun MaterialSectionsList (
    sections: List<MaterialSection>,
    onClick: (MaterialSection) -> Unit,
){

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        sections.forEach { section ->

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .border(1.dp, Color.Black, RoundedCornerShape(20.dp))
                    .padding(10.dp)
                    .clickable {
                        onClick(section)
                    },
                contentAlignment = Alignment.CenterStart
            ){
                Text(text = section.name)

            }

        }
    }

}