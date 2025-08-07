package com.example.sdo_project.presentation.add_material.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.sdo_project.presentation.common.CommonTextField
import java.time.LocalTime

@Composable
fun ManualTimePicker(
    selectedTime: LocalTime,
    onTimeChange : (LocalTime) -> Unit
) {

    var hour by remember { mutableStateOf(selectedTime.hour.toString()) }
    var minute by remember { mutableStateOf(selectedTime.minute.toString()) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly

    ) {

        CommonTextField(
            modifier = Modifier.weight(1f),
            text = hour,
            onChangeText = {newValue ->
                if (newValue.isEmpty() || newValue.toIntOrNull() in 0..23){
                    hour = newValue
                    updateTime(hour, minute, onTimeChange)
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),

        )

        Text(":", modifier = Modifier.padding(horizontal = 8.dp))

        CommonTextField(
            modifier = Modifier.weight(1f),
            text = minute,
            onChangeText = {newValue ->
                if (newValue.isEmpty() || newValue.toIntOrNull() in 0..59){
                    minute = newValue
                    updateTime(hour, minute, onTimeChange)
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }

}

private fun updateTime(
    _hour: String,
    _minute: String,
    onTimeChange: (LocalTime) -> Unit
){
    val hour = _hour.toIntOrNull() ?: 0
    val minute = _minute.toIntOrNull() ?: 0
    onTimeChange(LocalTime.of(hour.coerceIn(0,23), minute.coerceIn(0,59)))
}