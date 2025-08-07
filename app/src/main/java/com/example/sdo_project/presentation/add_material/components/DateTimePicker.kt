package com.example.sdo_project.presentation.add_material.components


import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.sdo_project.presentation.common.CommonTextField
import com.example.sdo_project.presentation.common.DesButton
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTimePicker(
    setDateTime: (String) -> Unit
){

    val datePickerState = rememberDatePickerState()

    var showDatePicker by remember { mutableStateOf(false) }
    //var showTimePicker by remember { mutableStateOf(false) }

    var selectedDate = remember(datePickerState.selectedDateMillis) {
        datePickerState.selectedDateMillis?.let {millis ->
            Instant.ofEpochMilli(millis)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
        } ?: LocalDate.now()

    }
    var selectedTime by remember { mutableStateOf(LocalTime.now()) }

    val formattedTimeStamp = remember (selectedDate, selectedTime){
        LocalDateTime.of(selectedDate, selectedTime)
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"))
    }

    var textFieldSize by remember { mutableStateOf(IntSize.Zero) }


    Column(
        modifier = Modifier.wrapContentSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

            Box(
                modifier = Modifier
                    .size(
                        width = with(LocalDensity.current){textFieldSize.width.toDp()},
                        height = with(LocalDensity.current){textFieldSize.height.toDp()/2})

                    .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(5.dp))

                    .clickable { showDatePicker = true},
                contentAlignment = Alignment.CenterStart

            ) {
                Text("Выбрать Дату",
                    modifier = Modifier.padding(start = 10.dp))
            }


            if (showDatePicker){
                DatePickerDialog(
                    modifier = Modifier.padding(10.dp),
                    onDismissRequest = {showDatePicker = false},
                    confirmButton = {
                        TextButton(
                            onClick = {
                                showDatePicker = false
                                //showTimePicker = true
                            }
                        ) {
                            Text("OK")
                        }
                    }
                ) {
                    DatePicker(
                        state = datePickerState,

                        )
                }
            }

            ManualTimePicker(
                selectedTime =  selectedTime,
                onTimeChange = {newValue ->
                    selectedTime = newValue
                }
            )


            CommonTextField(

                text = "Итоговые даты и время: " + formattedTimeStamp,
                onChangeText = {},
                readOnly = true,
                modifier = Modifier.fillMaxWidth()
                    .onGloballyPositioned { coords ->
                        textFieldSize = coords.size
                    }

            )

            DesButton(
                inText = "время выбрано",
                onClick = {setDateTime(formattedTimeStamp)}
            )

    }


}