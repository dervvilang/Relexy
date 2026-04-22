package com.example.relexy.feature.learn

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.relexy.R
import com.example.relexy.core.ui.components.TopBar
import com.example.relexy.core.ui.components.WordsProgressBar
import com.example.relexy.core.ui.theme.RelexyTheme

@Composable
fun LearnNewWordsScreen(
    onBackClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 12.dp)
            .pointerInput(Unit) { detectTapGestures { focusManager.clearFocus() }},
        contentAlignment = Alignment.TopCenter,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopBar(
                title = stringResource(R.string.learn_new_words_title),
                leftContent = {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier.size(50.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_arrow_right_circle),
                            contentDescription = null,
                            modifier = Modifier.size(50.dp)
                        )
                    }
                }
            )

            WordsProgressBar(
                textRes = R.string.learn_new_words_learned,
                repeatedWords = 5,
                totalWords = 500
            )
        }
    }
}



@Preview
@Composable
fun LearnNewWordsScreenPreview(){
    RelexyTheme() {
        LearnNewWordsScreen({})
    }
}