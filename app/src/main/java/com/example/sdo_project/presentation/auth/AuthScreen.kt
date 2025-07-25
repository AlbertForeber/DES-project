package com.example.sdo_project.presentation.auth

import android.accounts.AuthenticatorException
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sdo_project.ui.theme.SDOprojectTheme

@Composable
fun AuthScreen(
    event: ( AuthEvent ) -> Unit,

) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Butt
    }
}

@Preview(showBackground = true)
@Composable
fun AuthScreenPreview() {
}