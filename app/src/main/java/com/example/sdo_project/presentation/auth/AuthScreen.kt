package com.example.sdo_project.presentation.auth

import android.accounts.AuthenticatorException
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sdo_project.presentation.auth.components.AuthTextField
import com.example.sdo_project.presentation.common.DesButton
import com.example.sdo_project.ui.theme.SDOprojectTheme

@Composable
fun AuthScreen(
    state: AuthState,
    navigateToHome: () -> Unit,
    event: ( AuthEvent ) -> Unit,
) {

    val result = handleAuthResult(state, navigateToHome)
    if (result) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                AuthTextField(
                    email,
                    { email = it },
                    placeholder = "email"
                )
                AuthTextField(
                    password,
                    { password = it },
                    placeholder = "password"
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    DesButton(
                        onClick = { event(AuthEvent.Login(email, password)) },
                        inText = "Login"
                    )
                    DesButton(
                        onClick = { event(AuthEvent.Reset(email)) },
                        inText = "Reset"
                    )
                }
            }

        }
    }


    
}

@Preview(showBackground = true)
@Composable
fun AuthScreenPreview() {
    AuthScreen(
        state = AuthState.Idle,
        navigateToHome = {},
        event = {}
    )
}



@Composable
private fun handleAuthResult(state: AuthState, navigateToHome: () -> Unit): Boolean {
    val context = LocalContext.current
    return when (state) {
        is AuthState.Authorized -> {
            navigateToHome()
            false
        }
        is AuthState.Loading -> {
            CircularProgressIndicator()
            false
        }
        is AuthState.Error -> {
            Toast.makeText(context, state.errorMessage, Toast.LENGTH_SHORT).show()
            true
        }
        is AuthState.Idle -> {
            true
        }
    }
}

