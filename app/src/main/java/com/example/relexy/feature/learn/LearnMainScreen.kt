package com.example.relexy.feature.learn

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.relexy.R
import com.example.relexy.core.ui.components.BrandTitle
import com.example.relexy.core.ui.components.PrimaryCard
import com.example.relexy.core.ui.theme.RelexyTheme

@Composable
fun LearnMainScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
            .padding(all = 16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(Modifier.height(24.dp))
            BrandTitle()
            Text(
                text = stringResource(R.string.learn_interval_repetition),
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(Modifier.height(13.dp))
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
}

@Preview
@Composable
fun LearnMainScreenPreview() {
    RelexyTheme() {
        LearnMainScreen()
    }
}