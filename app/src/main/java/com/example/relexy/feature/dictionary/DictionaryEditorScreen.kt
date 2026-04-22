package com.example.relexy.feature.dictionary

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.relexy.R
import com.example.relexy.core.ui.components.TopBar
import com.example.relexy.core.ui.components.textFields.AppTextField
import com.example.relexy.core.ui.components.textFields.SearchTextField
import com.example.relexy.core.ui.theme.RelexyTheme
import com.example.relexy.domain.model.DictionaryIcons
import com.example.relexy.feature.dictionary.ui.components.DictionaryIconPickerCard

@Composable
fun DictionaryEditorScreen(
    onBackClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    var searchText by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp)
            .pointerInput(Unit) { detectTapGestures { focusManager.clearFocus() } },
        contentAlignment = Alignment.TopCenter,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopBar(
                title = stringResource(R.string.edit_dictionary_title),
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

            Spacer(Modifier.height(24.dp))

            AppTextField(
                value = searchText,
                onValueChange = { searchText = it },
                placeholder = stringResource(R.string.dictionary_title_placeholder)
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.dictionary_icon),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            DictionaryIconPickerCard(
                icons = DictionaryIcons.all,
                selectedIconRes = null,
                onIconClick = { clickedIconRes ->

                }
            )
        }
    }
}

@Preview
@Composable
fun DictionaryEditorScreenPreview() {
    RelexyTheme() {
        DictionaryEditorScreen({})
    }
}