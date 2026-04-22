package com.example.relexy.core.ui.components.buttons

import android.widget.ImageButton
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.relexy.core.ui.theme.RelexyTheme

@Composable
fun AddRoundButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val background = MaterialTheme.colorScheme.background

    Box(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .height(90.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            background.copy(alpha = 0.70f),
                            background.copy(alpha = 0.92f)
                        )
                    )
                )
        )

        FloatingActionButton(
            onClick = onClick,
            modifier = Modifier
                //.padding(vertical = 6.dp)
                .size(50.dp)
                .align(Alignment.BottomCenter),
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary,
            shape = CircleShape,
            elevation = FloatingActionButtonDefaults.elevation(
                defaultElevation = 4.dp
            )
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier.size(36.dp)
            )
        }
    }
}

@Preview
@Composable
fun AddRoundButtonPreview() {
    RelexyTheme() {
        AddRoundButton({})
    }
}