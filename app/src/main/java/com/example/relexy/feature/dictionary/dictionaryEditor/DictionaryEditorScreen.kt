package com.example.relexy.feature.dictionary.dictionaryEditor

import android.content.Context
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.relexy.R
import com.example.relexy.core.ui.components.TopBar
import com.example.relexy.core.ui.components.buttons.PrimaryButtonWithBlur
import com.example.relexy.core.ui.components.textFields.AppTextField
import com.example.relexy.core.ui.theme.RelexyTheme
import com.example.relexy.domain.roughModels.DictionaryIcons
import com.example.relexy.feature.dictionary.ui.components.DictionaryIconPickerCard

@Composable
fun DictionaryEditorScreen(
    uiState: DictionaryEditorUiState,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onIconSelected: (String) -> Unit,
    onSaveClick: () -> Unit,
    onBackClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

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
                title = if (uiState.isEditMode) {
                    "Редактирование словаря"
                } else {
                    "Создание словаря"
                },
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
                value = uiState.title,
                onValueChange = onTitleChange,
                placeholder = "Название словаря"
            )

            if (uiState.titleError != null) {
                Spacer(Modifier.height(6.dp))
                Text(
                    text = uiState.titleError,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error
                )
            }

            Spacer(Modifier.height(14.dp))

            AppTextField(
                value = uiState.description,
                onValueChange = onDescriptionChange,
                placeholder = "Описание (необязательно)"
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = "Иконка словаря",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(Modifier.height(8.dp))

            DictionaryIconPickerCard(
                icons = DictionaryIcons.all,
                selectedIconRes = uiState.selectedIconKey.toDictionaryIconRes(context),
                onIconClick = { clickedIconRes ->
                    onIconSelected(clickedIconRes.toDictionaryIconKey(context))
                }
            )

            if (uiState.errorMessage != null) {
                Spacer(Modifier.height(14.dp))
                Text(
                    text = uiState.errorMessage,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        PrimaryButtonWithBlur(
            text = if (uiState.isSaving) "Сохранение..." else "Сохранить",
            onClick = onSaveClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .align(Alignment.BottomCenter)
        )
    }
}

private fun Int.toDictionaryIconKey(context: Context): String {
    return context.resources.getResourceEntryName(this)
}

private fun String.toDictionaryIconRes(context: Context): Int? {
    val resId = context.resources.getIdentifier(
        this,
        "drawable",
        context.packageName
    )
    return resId.takeIf { it != 0 }
}

@Preview
@Composable
private fun DictionaryEditorScreenPreview() {
    RelexyTheme {
        DictionaryEditorScreen(
            uiState = DictionaryEditorUiState(
                title = "Мой словарь",
                description = "Описание словаря",
                selectedIconKey = "ic_for_dictionaries_airplane",
                isEditMode = false
            ),
            onTitleChange = {},
            onDescriptionChange = {},
            onIconSelected = {},
            onSaveClick = {},
            onBackClick = {}
        )
    }
}