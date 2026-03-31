package com.example.relexy.feature.dictionary

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.relexy.R
import com.example.relexy.core.ui.components.SortModeButton
import com.example.relexy.core.ui.components.TopBar
import com.example.relexy.core.ui.components.WordCard
import com.example.relexy.core.ui.components.next
import com.example.relexy.core.ui.theme.RelexyTheme
import com.example.relexy.domain.model.Word
import com.example.relexy.domain.model.WordStatus

@Composable
fun DictionaryScreen(
    onBackClick: () -> Unit
) {
    var sortMode by rememberSaveable {
        mutableStateOf(DictionarySortMode.BY_STATUS)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp),
        contentAlignment = Alignment.TopCenter,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TopBar(
                title = stringResource(R.string.dictionary_title_placeholder),
                leftContent = {
                    IconButton(
                        onBackClick, Modifier.size(50.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_arrow_right_circle),
                            contentDescription = null,
                            modifier = Modifier.size(50.dp)
                        )
                    }
                },
                rightContent = {
                    IconButton(
                        onClick = {},
                        modifier = Modifier.size(50.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_more_circle_1),
                            contentDescription = null,
                            modifier = Modifier.size(50.dp)
                        )
                    }
                }
            )

            Spacer(Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.End
            ) {
                SortModeButton(
                    sortMode = sortMode,
                    onClick = {
                        sortMode = sortMode.next()
                    }
                )
            }

            WordCard(
                item = Word(
                    id = "1",
                    originalText = "Word",
                    translationText = "Слово",
                    status = WordStatus.NEW
                ),
                onClick = {},
            )
        }
    }
}


@Preview
@Composable
fun DictionaryScreenPreview() {
    RelexyTheme() {
        DictionaryScreen(onBackClick = {})
    }
}
