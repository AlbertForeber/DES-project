package com.example.sdo_project.presentation.home.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sdo_project.domain.models.Discipline

fun Modifier.shimmerEffect() = composed{
    val transition = rememberInfiniteTransition()
    val alpha = transition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(delayMillis = 1000),
            repeatMode = RepeatMode.Reverse
        )
    ).value
    background(color = MaterialTheme.colorScheme.primary.copy(alpha), shape = RoundedCornerShape(20.dp))
}

@Composable
fun DisciplineCard (
    discipline: Discipline,
    onClick: (Discipline) -> Unit
) {
    Box(
        modifier = Modifier.height(100.dp).fillMaxWidth()
            .clickable {
                onClick(discipline)
            }
            .border(1.dp, Color.Black, RoundedCornerShape(20.dp))
            .padding(PaddingValues(top = 10.dp, start = 20.dp, end = 20.dp))

    ) {

        Column(
            horizontalAlignment = Alignment.Start,

        ) {
            Text(text = discipline.name)

            Spacer(modifier = Modifier.height(5.dp).background(Color.Transparent).fillMaxWidth(), ) // for constraints SpacerHeight
            Spacer(modifier = Modifier.height(1.dp).background(Color.Black).fillMaxWidth(), )
            Spacer(modifier = Modifier.height(5.dp).background(Color.Transparent).fillMaxWidth(), ) // for constraints SpacerHeight

            Text(text = discipline.departmentName)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DisciplineCardShimmer(){

        Box(
            modifier = Modifier.height(100.dp).fillMaxWidth()
                .padding(PaddingValues(top = 10.dp, start = 20.dp, end = 20.dp))
                .shimmerEffect()
        ) {

        }


}