package com.example.sdo_project.presentation.reset

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.sdo_project.presentation.MainEvent
import com.example.sdo_project.presentation.auth.AuthEvent
import com.example.sdo_project.presentation.auth.AuthState
import com.example.sdo_project.presentation.auth.components.AuthTextField
import com.example.sdo_project.presentation.common.DesButton
import kotlinx.coroutines.delay

@Composable

fun ResetScreen(
    isReset: MutableState<Boolean>,
    state: ResetState,
    resetEvent: (ResetEvent) -> Unit,
    navigateToAuth: () -> Unit
) {
    isReset.value = true
    LaunchedEffect(Unit) {
        delay(300)
        resetEvent(ResetEvent.Check)
    }

    val result = handleRecheckResult(
        isReset,
        state,
        navigateToAuth
    )

    if (result) {
        //
        var newPassword by remember { mutableStateOf("") }
        var newPasswordConfirmation by remember { mutableStateOf("") }
        //
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AuthTextField(
                newPassword,
                { newPassword = it },
                placeholder = "New password"
            )

            AuthTextField(
                newPasswordConfirmation,
                { newPasswordConfirmation = it },
                placeholder = "Confirm new password"
            )
            if (newPassword == newPasswordConfirmation) {
                DesButton(
                    "Change Password",
                    onClick = {
                        resetEvent(ResetEvent.ChangePassword(newPassword))
                    }
                )
            }
        }
    }
    else {
        CircularProgressIndicator()
    }
}


@Composable
fun handleRecheckResult(
    isReset: MutableState<Boolean>,
    state: ResetState,
    navigateToAuth: () -> Unit
): Boolean {
    val context = LocalContext.current
    return when (state) {
        ResetState.Allowed -> {
            true
        }
        is ResetState.Error -> {
            Toast.makeText(context, state.errorMessage, Toast.LENGTH_SHORT).show()
            true
        }
        ResetState.Loading -> {
            false
        }
        ResetState.Success -> {
            isReset.value = false
            false
        }
        ResetState.WrongLink -> {
            isReset.value = false
            navigateToAuth()
            false
        }
    }
}
