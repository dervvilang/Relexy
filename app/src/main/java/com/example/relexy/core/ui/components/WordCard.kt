package com.example.relexy.core.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.relexy.R
import com.example.relexy.core.ui.theme.RelexyIndicatorLearning
import com.example.relexy.core.ui.theme.RelexyIndicatorMastered
import com.example.relexy.core.ui.theme.RelexyIndicatorNew
import com.example.relexy.core.ui.theme.RelexyTheme
import com.example.relexy.data.local.entity.WordStatus
import com.example.relexy.domain.model.WordListItem

@Composable
fun WordCard(
    item: WordListItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            Box(
                modifier = Modifier
                    .width(20.dp)
                    .fillMaxHeight()
                    .defaultMinSize(minHeight = 76.dp)
                    .background(
                        color = item.status.indicatorColor()
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 6.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(item.status.titleRes()),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(0.7f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = item.originalText,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = item.translationText,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
    }
}

@StringRes
fun WordStatus.titleRes(): Int {
    return when (this) {
        WordStatus.NEW -> R.string.status_new
        WordStatus.LEARNING -> R.string.status_learning
        WordStatus.MASTERED -> R.string.status_mastered
    }
}

fun WordStatus.indicatorColor(): Color {
    return when (this) {
        WordStatus.NEW -> RelexyIndicatorNew
        WordStatus.LEARNING -> RelexyIndicatorLearning
        WordStatus.MASTERED -> RelexyIndicatorMastered
    }
}

@Preview(showBackground = true)
@Composable
private fun WordCardPreview() {
    RelexyTheme {
        WordCard(
            item = WordListItem(
                id = "1",
                originalText = "Word",
                translationText = "Слово",
                status = WordStatus.LEARNING
            ),
            onClick = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}