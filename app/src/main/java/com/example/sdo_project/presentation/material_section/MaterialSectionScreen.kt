package com.example.sdo_project.presentation.material_section

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sdo_project.domain.models.Material
import com.example.sdo_project.domain.models.MaterialSection
import com.example.sdo_project.presentation.common.error_screen.ErrorScreen
import com.example.sdo_project.presentation.common.LoadingElement
import com.example.sdo_project.presentation.common.MaterialCard
import com.example.sdo_project.presentation.common.SectionCard
import com.example.sdo_project.presentation.material_section.components.BackStackText
import com.example.sdo_project.presentation.material_section.components.MaterialsIconButton

@Composable
fun MaterialSectionScreen(
    initialParent: MaterialSection,
    event: (MaterialSectionEvent ) -> Unit,
    state: MaterialSectionState,
    backStack: List<MaterialSection>,
    navigateToDiscipline: () -> Unit
) {
    LaunchedEffect(Unit) {
        event(MaterialSectionEvent.Update(initialParent))
    }




    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(1.dp)
        ) {

            MaterialsIconButton(
                Icons.Default.Home,
                onClick = {
                    navigateToDiscipline()
                }
            )
            Text("/")

            if (backStack.isNotEmpty()) {
                if (backStack.size > 3) {
                    Text("../")
                    backStack.subList(backStack.lastIndex - 2, backStack.lastIndex).forEach {
                        BackStackText(it.name) { event(MaterialSectionEvent.BackTo(it)) }
                    }
                }
                else {
                    backStack.subList(0, backStack.lastIndex).forEach {
                        BackStackText(it.name) { event(MaterialSectionEvent.BackTo(it)) }
                    }
                }
            }



        }

        HorizontalDivider()
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .weight(13f)
        ) {
            when (state) {
                is MaterialSectionState.Error -> {
                    ErrorScreen(error = state.errorMessage, retryButtonEnabled = true) { event(MaterialSectionEvent.Update(null)) }
                }
                is MaterialSectionState.Idle -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        state.availableSections.forEach {
                            item {
                                SectionCard(it.name) {
                                    event(MaterialSectionEvent.Next(it))
                                }
                            }
                        }
                        item {
                            HorizontalDivider()
                        }
                        state.availableMaterials.forEach {
                            item {
                                MaterialCard(it)
                            }
                        }
                    }
                }
                MaterialSectionState.Loading -> {
                    LoadingElement( modifier = Modifier.fillMaxSize() )
                }

            }
        }

    }

}


@Preview(showBackground = true)
@Composable
fun MaterialSectionScreenPreview() {
    MaterialSectionScreen(
        MaterialSection(
            id = 2,
            name = "FirstSect",
            parentId = 1,
            disciplineId = 1
        ),
        event = { },
        state = MaterialSectionState.Idle(
            availableSections = listOf(
                MaterialSection(
                    id = 3,
                    name = "ExampleSection",
                    parentId = 2,
                    disciplineId = 1
                ),
                MaterialSection(
                    id = 4,
                    name = "ExampleSection",
                    parentId = 2,
                    disciplineId = 1
                )
            ),
            availableMaterials = listOf(
                Material(
                    id = 1,
                    name = "ExampleMaterial",
                    sectionId = 2,
                    accessTime = "11.11.11",
                    content = ""
                )
            )
        ),
        backStack = listOf(
            MaterialSection(
                id = 1,
                name = "ExampleSection",
                parentId = null,
                disciplineId = 1
            ),
            MaterialSection(
                id = 2,
                name = "ExampleSection",
                parentId = 1,
                disciplineId = 1
            ),
            MaterialSection(
                id = 2,
                name = "ExampleSection",
                parentId = 1,
                disciplineId = 1
            )


        ),
        navigateToDiscipline = {}
    )
}