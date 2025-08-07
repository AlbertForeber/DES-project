package com.example.sdo_project.presentation.add_material

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.sdo_project.domain.models.Discipline
import com.example.sdo_project.domain.models.MaterialSection
import com.example.sdo_project.presentation.add_material.components.TypeManySectionsPart
import com.example.sdo_project.presentation.add_material.components.TypeMaterialPart
import com.example.sdo_project.presentation.add_material.components.TypeSectionPart
import com.example.sdo_project.presentation.common.LoadingElement
import com.example.sdo_project.presentation.common.error_screen.ErrorScreen

@Composable
fun AddMaterialScreen(
    initialParent: MaterialSection? = null,
    mapOfTypeAddingItems: Map<String, TypeAddingItem>,
    addMaterial: (MaterialSection, String, String, String) -> Unit,
    discipline: Discipline?= null,
    addSection: (Discipline?, String, MaterialSection?) -> Unit,
    addSections: (Discipline?, List<String>, MaterialSection?) -> Unit,
    state: AddMaterialState,
    moveBack: ()-> Unit,



){

    val (selectedType, onTypeSelected) = remember { mutableStateOf(mapOfTypeAddingItems.get("Директория")!!) }


    Column(
        modifier = Modifier.fillMaxSize().padding(10.dp),

    ) {
        when(state){
            is AddMaterialState.Error -> {
                ErrorScreen(
                    error= "Возникла Ошибка. Попробовать позже. Вернутся обратно",
                    retryButton = moveBack,
                    retryButtonEnabled = true
                )
            }
            AddMaterialState.Loading -> {
                LoadingElement()
            }
            AddMaterialState.Start -> {

                ///-------------choose type of Adding item-------------
                mapOfTypeAddingItems.forEach { (key, value) ->

                    // опции добавления материала не будет доступна на главном экране дисциплины
                    if (!(initialParent == null && value == TypeAddingItem.Material)){
                        Row(
                            modifier = Modifier.fillMaxWidth()
                                .selectable(
                                    selected = (value == selectedType),
                                    onClick = {onTypeSelected(value)}
                                ),

                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            RadioButton(
                                selected = (value == selectedType),
                                onClick =  null
                            )

                            Text(text  = key)
                        }
                    }

                }


                // part depending on typeAddingItem
                HandleTypeAddingItem(
                    type = selectedType,
                    initialParent = initialParent,
                    addMaterial = addMaterial,
                    discipline = discipline,
                    addSection = addSection,
                    addSections = addSections,
                    moveBack = moveBack
                )
            }

            AddMaterialState.Success -> {

                val context = LocalContext.current

                Toast.makeText(context,
                    "Добавлено",
                    Toast.LENGTH_LONG).show()

                moveBack()

            }
        }

    }

}

@Composable
fun HandleTypeAddingItem(
    type: TypeAddingItem,
    initialParent: MaterialSection?,
    addMaterial: (MaterialSection, String, String, String) -> Unit,
    discipline: Discipline?,
    addSection: (Discipline?, String, MaterialSection?) -> Unit,
    addSections: (Discipline?, List<String>, MaterialSection?) -> Unit,
    moveBack: ()-> Unit,

){
    when (type){
        is TypeAddingItem.Material -> {
            if (initialParent != null) {
                TypeMaterialPart(
                    addMaterial = addMaterial,
                    initialParent = initialParent,
                )
            }else {
                ErrorScreen(
                    error = "Возникла Ошибка. Попробуйте позже. Вернуться обратно.",
                    retryButtonEnabled = true,
                    retryButton = moveBack
                )
            }

        }
        is TypeAddingItem.Section -> {
            TypeSectionPart(initialParent = initialParent,
                discipline = discipline,
                addSection = addSection)
        }
        is TypeAddingItem.ManySections -> {
            TypeManySectionsPart(
                initialParent  = initialParent,
                discipline = discipline,
                addSections = addSections
            )
        }
    }
}