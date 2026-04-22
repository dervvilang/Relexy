package com.example.relexy.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.relexy.R
import com.example.relexy.core.ui.components.buttons.SecondaryButton
import com.example.relexy.core.ui.theme.RelexyTheme

@Composable
fun TodayCard(
    modifier: Modifier = Modifier,
    learnedWordsCount: Int,
    totalWordsCount: Int,
) {
    val shape = RoundedCornerShape(16.dp)

    val primaryColor = MaterialTheme.colorScheme.primary
    val secondaryColor = MaterialTheme.colorScheme.background
    val textColor = MaterialTheme.colorScheme.onPrimary

    Surface(
        modifier = modifier,
        shape = shape,
        shadowElevation = 4.dp,
        //color = Color.Transparent
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .drawWithCache {
                    val brush = Brush.linearGradient(
                        colors = listOf(primaryColor, secondaryColor),
                        start = Offset(0f, size.height),
                        end = Offset(size.width, 0f)
                    )

                    onDrawBehind {
                        drawRect(brush = brush)
                    }
                }
                .padding(horizontal = 18.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.ic_brandicon_fixed),
                contentDescription = null,
                modifier = Modifier.size(110.dp)
            )

            Spacer(modifier = Modifier.width(24.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = stringResource(R.string.learn_today_title),
                    color = textColor,
                    style = MaterialTheme.typography.titleLarge.copy(
                        shadow = Shadow(
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
                            offset = Offset(1.5f, 4f),
                            blurRadius = 7f
                        )
                    )
                )

                Text(
                    text = stringResource(R.string.learn_new_words_progress,
                        learnedWordsCount,
                        totalWordsCount),
                    color = textColor,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        shadow = Shadow(
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
                            offset = Offset(1.5f, 4f),
                            blurRadius = 7f
                        )
                    )
                )

                SecondaryButton(
                    text = stringResource(R.string.action_start),
                    onClick = { },
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TodayCardPreview() {
    RelexyTheme {
        TodayCard(learnedWordsCount = 5, totalWordsCount = 10)
    }
}