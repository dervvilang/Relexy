package com.example.relexy.core.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.relexy.R
import com.example.relexy.core.ui.theme.RelexyBtn

@Composable
fun BrandIcon(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(120.dp)
            .background(
                color = RelexyBtn,
                shape = RoundedCornerShape(22.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.ic_brandicon_fixed),
            contentDescription = null,
            modifier = Modifier.size(110.dp)
        )
    }
}