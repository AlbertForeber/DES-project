package com.example.sdo_project.presentation.common.error_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sdo_project.presentation.common.DesButton

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    error: String,
    sizeParameter: SizeParameter = SizeParameter.LargeSize,
    retryButtonEnabled: Boolean = false,
    retryButton: () -> Unit = {}
) {


    val constraints = when(sizeParameter) {
        SizeParameter.LargeSize -> Constraints(
            iconSize = 50.dp,
            textStyle = MaterialTheme.typography.displaySmall,
            maxTextSize = 120.dp
        )
        SizeParameter.MediumSize -> Constraints(
            iconSize = 40.dp,
            textStyle = MaterialTheme.typography.headlineMedium,
            maxTextSize = 90.dp
        )
        SizeParameter.SmallSize -> Constraints(
            iconSize = 35.dp,
            textStyle = MaterialTheme.typography.headlineSmall,
            maxTextSize = 50.dp
        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Icon(
            Icons.Default.Warning,
            null,
            modifier = Modifier.size(constraints.iconSize),
            tint = MaterialTheme.colorScheme.error
        )
        Text(
            "Something went wrong",
            textAlign = TextAlign.Center,
            style = constraints.textStyle
        )

        Text(
            "Error: $error",
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.heightIn(15.dp, constraints.maxTextSize)
        )
        if (retryButtonEnabled) {
            DesButton("Retry", retryButton)
        }



    }
}

@Preview(showBackground = true)
@Composable
fun LargeErrorScreenPreview() {
    ErrorScreen(
        error = "Very Big Erroooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo",
        retryButton = {},
        sizeParameter = SizeParameter.LargeSize
    )
}

@Preview(showBackground = true)
@Composable
fun MediumErrorScreenPreview() {
    ErrorScreen(
        error = "Very Big Erroooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo",
        retryButton = {},
        sizeParameter = SizeParameter.MediumSize
    )
}

@Preview(showBackground = true)
@Composable
fun SmallErrorScreenPreview() {
    ErrorScreen(
        error = "Very Big Erroooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo",
        retryButton = {},
        sizeParameter = SizeParameter.SmallSize
    )
}