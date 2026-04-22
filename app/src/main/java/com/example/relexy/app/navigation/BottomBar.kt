package com.example.relexy.app.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.relexy.core.ui.theme.RelexyTheme

@Composable
fun FloatingBottomBarOverlay(
    currentRoute: String?,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val background = MaterialTheme.colorScheme.background

    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(132.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            background.copy(alpha = 0.80f),
                            background.copy(alpha = 0.92f)
                        )
                    )
                )
        )

        BottomBar(
            currentRoute = currentRoute,
            onItemClick = onItemClick,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp)
        )
    }
}

@Composable
fun BottomBar(
    currentRoute: String?,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(16.dp),
                clip = false
            ),
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surface.copy(0.97f)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavigationItems.bottomDestinations.forEach { destination ->
                BottomBarItem(
                    destination = destination,
                    selected = currentRoute == destination.route,
                    onClick = { onItemClick(destination.route) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun BottomBarItem(
    destination: BottomDestination,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val selectedBackground = MaterialTheme.colorScheme.primary.copy(alpha = 0.10f)
    val selectedContentColor = MaterialTheme.colorScheme.primary
    val unselectedContentColor = MaterialTheme.colorScheme.secondary

    val backgroundColor = if (selected) selectedBackground else Color.Transparent
    val contentColor = if (selected) selectedContentColor else unselectedContentColor

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .clickable(onClick = onClick)
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = destination.iconRes),
            contentDescription = stringResource(id = destination.titleRes),
            tint = contentColor,
            modifier = Modifier.size(40.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = stringResource(destination.titleRes),
            color = contentColor,
            style = MaterialTheme.typography.labelSmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview
@Composable
private fun BottomBarPreview() {
    RelexyTheme {
        FloatingBottomBarOverlay(
            currentRoute = Destinations.LEARN_MAIN,
            onItemClick = {}
        )
    }
}