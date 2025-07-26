package com.example.sdo_project.presentation.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.sdo_project.presentation.auth.components.AuthTextField
import com.example.sdo_project.presentation.common.DesButton

@Composable

fun ResetScreen(
    state: AuthState,
    event: ( AuthEvent ) -> Unit,

) {
    LaunchedEffect(Unit) {
        event( AuthEvent.Recheck(isReset = true) )
    }

    val result = handleRecheckResult(state)
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
                        event(AuthEvent.ChangePassword(newPassword))
                    }
                )
            }
        }
    }
}


@Composable
fun handleRecheckResult(
    state: AuthState
): Boolean {
    return when (state) {
        is AuthState.Idle -> false
        is AuthState.Authorized -> true
        is AuthState.Error -> false
        is AuthState.Loading -> {
            CircularProgressIndicator()
            true
        }
    }
}
