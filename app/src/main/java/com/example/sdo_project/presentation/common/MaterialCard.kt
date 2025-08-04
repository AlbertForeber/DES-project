package com.example.sdo_project.presentation.common

import android.content.Intent
import android.net.Uri
import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sdo_project.domain.models.Material
import androidx.core.net.toUri

@Composable
fun MaterialCard(
    material: Material
) {
    val context = LocalContext.current
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .padding(0.dp, 5.dp)
            .clickable {
                Toast.makeText(context, "Redirecting...", Toast.LENGTH_SHORT).show()
                val intent = Intent(Intent.ACTION_VIEW, material.content.toUri())
                context.startActivity(intent)
                       },
        shape = MaterialTheme.shapes.small
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(Icons.Filled.Done, null)
            VerticalDivider(Modifier.padding(10.dp, 0.dp))

            Text(
                material.name,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(3f),
                overflow = TextOverflow.Ellipsis
            )
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Spacer(modifier = Modifier.size(20.dp))
                Text("Available till: ${material.accessTime}", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MaterialCardPreview() {
    MaterialCard(Material(
        id = 1,
        name = "Лето",
        sectionId = 1,
        accessTime = "01.09.2025",
        content = "https://google.com"
    ))
}