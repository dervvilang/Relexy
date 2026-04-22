package com.example.relexy.feature.dictionary

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.CleaningServices
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material.icons.outlined.Download
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Publish
import androidx.compose.material.icons.outlined.Restore
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.relexy.R
import com.example.relexy.core.ui.components.BottomSheetActionItem
import com.example.relexy.core.ui.components.BottomSheetActionList
import com.example.relexy.core.ui.components.BottomSheetCard
import com.example.relexy.core.ui.components.BottomSheetContent
import com.example.relexy.feature.dictionary.wordEditor.WordInputSection

@Composable
fun ActionIconTextItem(
    @DrawableRes iconRes: Int,
    title: String,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    iconTint: Color? = null
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 16.dp, horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(iconRes),
            contentDescription = null,
            modifier = Modifier.size(30.dp),
            tint = iconTint ?: MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.width(18.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}


/*DICTIONARY*/

@Composable
fun DictionaryMenuBottomSheetContent(
    onEditDictionary: () -> Unit,
    onResetDictionaryProgress: () -> Unit,
    onClearDictionary: () -> Unit,
    onImportNewWords: () -> Unit,
    onPublishDictionaryToProfile: () -> Unit,
    onShareDictionary: () -> Unit,
    onDeleteDictionary: () -> Unit,
) {
    val dividerColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
            .padding(horizontal = 20.dp),
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surface,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            ActionIconTextItem(
                iconRes = R.drawable.ic_edit_1,
                title = stringResource(R.string.action_edit_dictionary),
                onClick = onEditDictionary
            )

            HorizontalDivider(
                color = dividerColor,
                thickness = 1.dp
            )

            ActionIconTextItem(
                iconRes = R.drawable.ic_history_1,
                title = stringResource(R.string.action_reset_dictionary_progress),
                onClick = onResetDictionaryProgress
            )

            HorizontalDivider(
                color = dividerColor,
                thickness = 1.dp
            )

            ActionIconTextItem(
                iconRes = R.drawable.ic_erase_1,
                title = stringResource(R.string.action_clear_dictionary),
                onClick = onClearDictionary
            )

            HorizontalDivider(
                color = dividerColor,
                thickness = 1.dp
            )

            ActionIconTextItem(
                iconRes = R.drawable.ic_arrow_download,
                title = stringResource(R.string.action_import_new_words),
                onClick = onImportNewWords
            )

            HorizontalDivider(
                color = dividerColor,
                thickness = 1.dp
            )

            ActionIconTextItem(
                iconRes = R.drawable.ic_share_ios_1,
                title = stringResource(R.string.action_publish_in_profile),
                onClick = onPublishDictionaryToProfile
            )

            HorizontalDivider(
                color = dividerColor,
                thickness = 1.dp
            )

            ActionIconTextItem(
                iconRes = R.drawable.ic_share_1,
                title = stringResource(R.string.action_share_dictionary),
                onClick = onShareDictionary
            )

            HorizontalDivider(
                color = dividerColor,
                thickness = 1.dp
            )

            ActionIconTextItem(
                iconRes = R.drawable.ic_delete_1,
                title = stringResource(R.string.action_delete),
                onClick = onDeleteDictionary,
                iconTint = MaterialTheme.colorScheme.error
            )
        }
    }
}

@Composable
fun WordMenuBottomSheetContent(
    wordId: String,
    onResetWordProgress: () -> Unit,
    onMarkWordAsMastered: () -> Unit,
    onEditWord: () -> Unit,
    onDeleteWord: () -> Unit
) {
    val dividerColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
            .padding(horizontal = 20.dp),
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surface,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            ActionIconTextItem(
                iconRes = R.drawable.ic_history_1,
                title = stringResource(R.string.action_reset_progress),
                onClick = onResetWordProgress
            )

            HorizontalDivider(
                color = dividerColor,
                thickness = 1.dp
            )

            ActionIconTextItem(
                iconRes = R.drawable.ic_checkmark_square_1,
                title = stringResource(R.string.action_mark_mastered),
                onClick = onMarkWordAsMastered
            )

            HorizontalDivider(
                color = dividerColor,
                thickness = 1.dp
            )

            ActionIconTextItem(
                iconRes = R.drawable.ic_edit_1,
                title = stringResource(R.string.action_edit),
                onClick = onEditWord
            )

            HorizontalDivider(
                color = dividerColor,
                thickness = 1.dp
            )

            ActionIconTextItem(
                iconRes = R.drawable.ic_delete_1,
                title = stringResource(R.string.action_delete),
                onClick = onDeleteWord,
                iconTint = MaterialTheme.colorScheme.error
            )
        }
    }
}