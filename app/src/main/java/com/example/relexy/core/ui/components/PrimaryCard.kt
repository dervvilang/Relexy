package com.example.relexy.core.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import com.example.relexy.R
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.relexy.core.ui.theme.RelexyTheme

@Composable
fun PrimaryCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    title: String,
    @DrawableRes leadingIcon: Int,
    @DrawableRes icons: List<Int>
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        shadowElevation = 4.dp,
        color = MaterialTheme.colorScheme.surface,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = leadingIcon),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            TrailingIconsGroup(icons = icons)
        }
    }
}

@Composable
private fun TrailingIconsGroup(
    @DrawableRes icons: List<Int>,
    iconSize: Dp = 36.dp,
    //iconSpacing: Dp = 2.dp,
    maxWidth: Dp = 100.dp,
    fadeWidth: Dp = 20.dp,
    modifier: Modifier = Modifier,
) {
    var containerWidthPx by remember { mutableIntStateOf(0) }
    var contentWidthPx by remember { mutableIntStateOf(0) }

    val shouldShowFade = contentWidthPx > containerWidthPx && containerWidthPx > 0

    val surfaceColor = MaterialTheme.colorScheme.surface

    Box(
        modifier = modifier
            .widthIn(max = maxWidth)
            .clipToBounds()
            .onSizeChanged { size ->
                containerWidthPx = size.width
            },
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier
                .wrapContentWidth(unbounded = true, align = Alignment.Start)
                .onSizeChanged { size ->
                    contentWidthPx = size.width
                },
            //horizontalArrangement = Arrangement.spacedBy(iconSpacing),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            icons.forEach { iconRes ->
                Image(
                    painter = painterResource(id = iconRes),
                    contentDescription = null,
                    modifier = Modifier.size(iconSize),
                    contentScale = ContentScale.Fit
                )
            }
        }

        if (shouldShowFade) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .drawWithCache {
                        val fadeWidthPx = fadeWidth.toPx()
                        val startX = (size.width - fadeWidthPx).coerceAtLeast(0f)

                        val brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color.Transparent,
                                surfaceColor.copy(alpha = 0.75f),
                                surfaceColor
                            ),
                            startX = startX,
                            endX = size.width
                        )

                        onDrawBehind {
                            drawRect(
                                brush = brush,
                                topLeft = Offset(startX, 0f),
                                size = Size(
                                    width = size.width - startX,
                                    height = size.height
                                )
                            )
                        }
                    }
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFE9EEF7)
@Composable
private fun PrimaryCardPreview() {
    RelexyTheme {
        PrimaryCard(
            title = "Выбрано 5 словарей",
            leadingIcon = R.drawable.ic_pencil_1,
            icons = listOf(
                R.drawable.ic_hospital,
                R.drawable.ic_bluebook,
                R.drawable.ic_americasglobe,
                R.drawable.ic_americasglobe,
            ),
            onClick = {}
        )
    }
}