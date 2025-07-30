package com.example.sdo_project.presentation.teacher_grade_screen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sdo_project.domain.models.Group

@Composable
fun GradeDropDown(
    groups: List<Group>,
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    onClick: ( Group ) -> Unit,
    modifier: Modifier = Modifier
) {
    Box {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = onDismissRequest
        ) {
            groups.forEach {
                DropdownMenuItem(
                    text = { Text( it.name ) },
                    onClick = {
                        onClick(it)
                        onDismissRequest()
                              },
                    modifier = modifier.padding(5.dp)
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGroupDropdown() {
    GradeDropDown(
        listOf(Group(
            id = 1,
            name = "ИКБО-41-24"
        )),
        true,
        {},
        {}
    )
}