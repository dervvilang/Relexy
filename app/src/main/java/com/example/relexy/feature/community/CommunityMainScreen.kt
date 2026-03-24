package com.example.relexy.feature.community

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.relexy.R
import com.example.relexy.core.ui.components.SearchCard
import com.example.relexy.core.ui.components.UserListCard
import com.example.relexy.core.ui.theme.RelexyTheme

@Composable
fun CommunityMainScreen() {
    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp)
            .padding(horizontal = 16.dp)
            .pointerInput(Unit) { detectTapGestures { focusManager.clearFocus() }},
        contentAlignment = Alignment.TopCenter
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Text(
                    text = stringResource(R.string.nav_community),
                    modifier = Modifier
                        .padding(start = 8.dp),
                    style = MaterialTheme.typography.displayLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(Modifier.height(28.dp))

            SearchCard(stringResource(R.string.community_search_hint))

            Spacer(Modifier.height(13.dp))

            Text(
                text = stringResource(R.string.community_subscriptions),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(Modifier.height(8.dp))

            UserListCard(
                onClick = {},
                title = stringResource(R.string.nickname_user, 1),
                leadIcon = R.drawable.ic_jackolantern
            )

            Spacer(Modifier.height(13.dp))

            UserListCard(
                onClick = {},
                title = stringResource(R.string.nickname_user, 2),
                leadIcon = R.drawable.ic_man
            )

            Spacer(Modifier.height(13.dp))

            UserListCard(
                onClick = {},
                title = stringResource(R.string.nickname_user, 3),
                leadIcon = R.drawable.ic_princess
            )
        }
    }
}

@Preview
@Composable
fun CommunityMainScreenPreview() {
    RelexyTheme() {
        CommunityMainScreen()
    }
}