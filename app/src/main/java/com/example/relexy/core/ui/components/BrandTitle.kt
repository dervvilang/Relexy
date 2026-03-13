package com.example.relexy.core.ui.components

import com.example.relexy.R
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.relexy.core.ui.theme.RelexyTextStyles
import com.example.relexy.core.ui.theme.RelexyTextStyles.BrandLogo
import com.example.relexy.core.ui.theme.RelexyTokens

@Composable
fun BrandTitle() {
    Text(
        text = stringResource(R.string.app_name),
        style = RelexyTextStyles.BrandLogo,
        color = RelexyTokens.title
    )
}