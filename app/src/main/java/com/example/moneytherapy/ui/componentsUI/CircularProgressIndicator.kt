package com.example.moneytherapy.ui.componentsUI

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.moneytherapy.ui.screens.GoalBox
import com.example.moneytherapy.ui.theme.MoneyTherapyTheme

@Composable
fun CircularProgressIndicator(progress: Float, modifier: Modifier) {
    Box(
        modifier = Modifier
            .size(100.dp),
        contentAlignment = Alignment.Center
    ) {
        androidx.compose.foundation.Canvas(
            modifier = Modifier.size(100.dp),
        ) {
            val strokeWidth = 12.dp.toPx()
            drawArc(
                color = Color.LightGray,
                startAngle = -90f,
                sweepAngle = 360f,
                useCenter = false,
                style = androidx.compose.ui.graphics.drawscope.Stroke(
                    width = strokeWidth,
                    cap = StrokeCap.Round
                )
            )
            drawArc(
                color = if (progress < 0.5f) Color.Red else Color.Green,
                startAngle = -90f,
                sweepAngle = 360f * progress,
                useCenter = false,
                style = androidx.compose.ui.graphics.drawscope.Stroke(
                    width = strokeWidth,
                    cap = StrokeCap.Round
                )
            )
        }

        Text(
            text = "${(progress * 100).toInt()}%",
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GoalBoxPreview() {
    MoneyTherapyTheme {
        GoalBox(
            title = "Exemplo de Objetivos",
            items = listOf(
                "Economizar para viagem" to 0.3f,
                "Comprar um carro" to 0.75f,
                "Reserva de emergência" to 0.5f
            )
        )
    }
}
