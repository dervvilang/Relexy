package com.example.relexy.core.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.tooling.preview.Preview
import com.example.relexy.core.ui.theme.RelexyTheme

@Composable
fun FlaskProgressIndicator(
    progress: Float,
    modifier: Modifier = Modifier,
) {
    val outlineColor = Color.White.copy(alpha = 0.95f)
    val highlightColor = Color.White.copy(alpha = 0.55f)
    val fillColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.35f)
    val glassBackgroundColor = Color.White.copy(alpha = 0.18f)

    Canvas(
        modifier = modifier.aspectRatio(1f)
    ) {
        val strokeWidth = size.minDimension * 0.045f
        val center = Offset(size.width / 2f, size.height / 2f)
        val radius = size.minDimension / 2f - strokeWidth / 2f

        val innerRadius = radius - strokeWidth / 2f
        val innerRect = Rect(
            left = center.x - innerRadius,
            top = center.y - innerRadius,
            right = center.x + innerRadius,
            bottom = center.y + innerRadius
        )

        // Клип по внутреннему кругу
        val clipCircle = Path().apply {
            addOval(innerRect)
        }

        // Уровень жидкости
        val fillTop = innerRect.bottom - innerRect.height * progress
        val waveAmplitude = innerRect.height * 0.16f

        clipPath(clipCircle) {
            drawCircle(
                color = glassBackgroundColor,
                radius = innerRadius,
                center = center
            )

            val liquidPath = Path().apply {
                moveTo(innerRect.left, innerRect.bottom)
                lineTo(innerRect.left, fillTop)

                cubicTo(
                    x1 = innerRect.left + innerRect.width * 0.25f,
                    y1 = fillTop - waveAmplitude,
                    x2 = innerRect.left + innerRect.width * 0.75f,
                    y2 = fillTop + waveAmplitude,
                    x3 = innerRect.right,
                    y3 = fillTop
                )

                lineTo(innerRect.right, innerRect.bottom)
                close()
            }

            drawPath(
                path = liquidPath,
                color = fillColor
            )
        }

        // Внешний контур
        drawCircle(
            color = outlineColor,
            radius = radius,
            center = center,
            style = Stroke(width = strokeWidth)
        )

        // Блик
        drawArc(
            color = highlightColor,
            startAngle = 130f,
            sweepAngle = 105f,
            useCenter = false,
            topLeft = Offset(
                x = innerRect.left + innerRect.width * 0.10f,
                y = innerRect.top + innerRect.height * 0.10f
            ),
            size = Size(
                width = innerRect.width * 0.70f,
                height = innerRect.height * 0.70f
            ),
            style = Stroke(
                width = strokeWidth * 0.9f,
                cap = StrokeCap.Round
            )
        )

        // Маленький дополнительный блик
        drawArc(
            color = highlightColor.copy(alpha = 0.35f),
            startAngle = 150f,
            sweepAngle = 28f,
            useCenter = false,
            topLeft = Offset(
                x = innerRect.left + innerRect.width * 0.18f,
                y = innerRect.top + innerRect.height * 0.18f
            ),
            size = Size(
                width = innerRect.width * 0.48f,
                height = innerRect.height * 0.48f
            ),
            style = Stroke(
                width = strokeWidth * 0.75f,
                cap = StrokeCap.Round
            )
        )

        // Пузырьки
        drawCircle(
            color = outlineColor.copy(alpha = 0.75f),
            radius = strokeWidth * 0.38f,
            center = Offset(
                x = center.x - innerRect.width * 0.18f,
                y = innerRect.bottom - innerRect.height * 0.14f
            )
        )

        drawCircle(
            color = outlineColor.copy(alpha = 0.65f),
            radius = strokeWidth * 0.28f,
            center = Offset(
                x = center.x + innerRect.width * 0.08f,
                y = innerRect.bottom - innerRect.height * 0.11f
            )
        )

        drawCircle(
            color = outlineColor.copy(alpha = 0.55f),
            radius = strokeWidth * 0.22f,
            center = Offset(
                x = center.x + innerRect.width * 0.26f,
                y = innerRect.bottom - innerRect.height * 0.22f
            )
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFF5F7FB
)
@Composable
fun FlaskProgressIndicatorP() {
    RelexyTheme() {
        FlaskProgressIndicator(0.5f)
    }
}