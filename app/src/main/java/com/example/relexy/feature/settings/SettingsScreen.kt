package com.example.relexy.feature.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.relexy.R
import com.example.relexy.core.ui.components.TopBar
import com.example.relexy.core.ui.theme.RelexyTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.relexy.feature.dictionary.DictionaryMenuBottomSheetContent
import com.example.relexy.feature.dictionary.DictionarySheet
import com.example.relexy.feature.dictionary.WordMenuBottomSheetContent

@Composable
fun SettingsScreen(
    onBackClick: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val dividerColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)

    var speakWordOnShow by rememberSaveable { mutableStateOf(true) }
    var isDarkTheme by rememberSaveable { mutableStateOf(true) }
    var notificationsEnabled by rememberSaveable { mutableStateOf(true) }

    var currentSheet by remember {
        mutableStateOf<DictionarySheet?>(null)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 12.dp)
            .verticalScroll(rememberScrollState())
            .pointerInput(Unit) { detectTapGestures { focusManager.clearFocus() } },
        contentAlignment = Alignment.TopCenter,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopBar(
                title = stringResource(R.string.settings_title),
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

            Spacer(Modifier.height(22.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.settings_learning_words),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    shape = RoundedCornerShape(16.dp),
                    shadowElevation = 4.dp,
                    color = MaterialTheme.colorScheme.surface,
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        SettingTextItem(
                            title = stringResource(R.string.settings_new_words_language_first),
                            subtitle = stringResource(R.string.language_english)
                        )

                        HorizontalDivider(color = dividerColor, thickness = 1.dp)

                        SettingTextItem(
                            title = stringResource(R.string.settings_new_words_mode),
                            subtitle = stringResource(R.string.mode_both_directions)
                        )

                        HorizontalDivider(color = dividerColor, thickness = 1.dp)

                        SettingTextItem(
                            title = stringResource(R.string.settings_review_mode),
                            subtitle = stringResource(R.string.mode_both_directions)
                        )

                        HorizontalDivider(color = dividerColor, thickness = 1.dp)

                        SettingTextItem(
                            title = stringResource(R.string.settings_interval_to_mastered),
                            subtitle = stringResource(R.string.days_count, 120)
                        )

                        HorizontalDivider(color = dividerColor, thickness = 1.dp)

                        SettingTextItem(
                            title = stringResource(R.string.settings_show_pictures),
                            subtitle = stringResource(R.string.settings_on_card_open)
                        )

                        HorizontalDivider(color = dividerColor, thickness = 1.dp)

                        SettingTextItem(
                            title = stringResource(R.string.settings_daily_goal),
                            subtitle = stringResource(R.string.goal_words, 10)
                        )

                        HorizontalDivider(color = dividerColor, thickness = 1.dp)

                        SettingTextItem(
                            title = stringResource(R.string.settings_give_words_for_review),
                            subtitle = stringResource(R.string.settings_from_all_dictionaries)
                        )

                        HorizontalDivider(color = dividerColor, thickness = 1.dp)

                        SettingCheckboxItem(
                            title = stringResource(R.string.settings_speak_word_on_show),
                            checked = speakWordOnShow,
                            onCheckedChange = { speakWordOnShow = it }
                        )
                    }
                }

                Spacer(Modifier.height(16.dp))

                Text(
                    text = stringResource(R.string.settings_parameters),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    shape = RoundedCornerShape(16.dp),
                    shadowElevation = 4.dp,
                    color = MaterialTheme.colorScheme.surface,
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        SettingSwitchItem(
                            title = stringResource(R.string.settings_theme_light_dark),
                            checked = isDarkTheme,
                            onCheckedChange = { isDarkTheme = it }
                        )

                        HorizontalDivider(color = dividerColor, thickness = 1.dp)

                        SettingSwitchItem(
                            title = stringResource(R.string.settings_enable_notifications),
                            checked = notificationsEnabled,
                            onCheckedChange = { notificationsEnabled = it }
                        )

                        HorizontalDivider(color = dividerColor, thickness = 1.dp)

                        SettingTextItem(
                            title = stringResource(R.string.settings_notification_frequency),
                            subtitle = stringResource(R.string.settings_frequency_every_1_hour)
                        )

                        HorizontalDivider(color = dividerColor, thickness = 1.dp)

                        SettingTextItem(
                            title = stringResource(R.string.settings_notification_period),
                            subtitle = "8:00 - 22:00"
                        )
                    }
                }
            }
        }

        /*currentSheet?.let { sheet ->
            ModalBottomSheet(
                onDismissRequest = {
                    currentSheet = null
                },
                sheetState = sheetState,
                containerColor = MaterialTheme.colorScheme.background,
                dragHandle = {
                    BottomSheetDefaults.DragHandle(
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            ) {
                when (sheet) {
                    DictionarySheet.DictionaryMenu -> {
                        DictionaryMenuBottomSheetContent(
                            onEditDictionary = {},
                            onResetDictionaryProgress = {},
                            onDeleteDictionary = {},
                            onClearDictionary = {},
                            onImportNewWords = {},
                            onPublishDictionaryToProfile = {},
                            onShareDictionary = {}
                        )
                    }

                    is DictionarySheet.WordMenu -> {
                        WordMenuBottomSheetContent(
                            wordId = sheet.wordId,
                            onResetWordProgress = {},
                            onMarkWordAsMastered = {},
                            onEditWord = { onGoToWordEditorScreen(sheet.wordId) },
                            onDeleteWord = {}
                        )
                    }
                }
            }
        }*/
    }
}

@Composable
fun SettingTextItem(
    title: String,
    subtitle: String,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 14.dp, vertical = 10.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(Modifier.height(2.dp))

        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 2.dp)
        )
    }
}

@Composable
fun SettingCheckboxItem(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colorScheme.primary,
                uncheckedColor = MaterialTheme.colorScheme.onBackground.copy(0.5f),
                checkmarkColor = MaterialTheme.colorScheme.onPrimary
            )
        )
    }
}

@Composable
fun SettingSwitchItem(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colorScheme.onPrimary,
                checkedTrackColor = MaterialTheme.colorScheme.primary,
                uncheckedThumbColor = MaterialTheme.colorScheme.surface,
                uncheckedTrackColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
            )
        )
    }
}


/*@Composable
fun onNewWordsFirstLanguageClick(

) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        shadowElevation = 4.dp,
        color = MaterialTheme.colorScheme.surface,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            SettingCheckboxItem(
                title = stringResource(R.string.settings_speak_word_on_show),
                checked = speakWordOnShow,
                onCheckedChange = { speakWordOnShow = it }
            )

        }
    }
}*/

@Preview
@Composable
fun SettingsScreenPreview() {
    RelexyTheme() {
        SettingsScreen({})
    }
}