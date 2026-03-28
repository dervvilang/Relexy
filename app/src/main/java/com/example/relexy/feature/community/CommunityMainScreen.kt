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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.example.relexy.domain.model.User

@Composable
fun CommunityMainScreen() {
    val focusManager = LocalFocusManager.current

    val users = listOf(
        User(id = "0", nickname = "nickname1", icon = R.drawable.ic_princess),
        User(id = "1", nickname = "nickname2", icon = R.drawable.ic_man),
        User(id = "2", nickname = "nickname3", icon = R.drawable.ic_wolfface),
        User(id = "3", nickname = "nickname4", icon = R.drawable.ic_jackolantern),
        User(id = "4", nickname = "nickname5", icon = R.drawable.ic_person_1),
        User(id = "5", nickname = "nickname6", icon = R.drawable.ic_dog),
        User(id = "6", nickname = "nickname7", icon = R.drawable.ic_jackolantern),
        User(id = "7", nickname = "nickname8", icon = R.drawable.ic_princess),
        User(id = "8", nickname = "nickname9", icon = R.drawable.ic_wolfface),
        User(id = "9", nickname = "nickname10", icon = R.drawable.ic_man),
        User(id = "10", nickname = "nickname11", icon = R.drawable.ic_princess),
        User(id = "11", nickname = "nickname12", icon = R.drawable.ic_dog),
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp)
            .padding(horizontal = 16.dp)
            .pointerInput(Unit) { detectTapGestures { focusManager.clearFocus() } },
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
                    .padding(start = 8.dp, bottom = 4.dp),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            LazyColumn(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (users.isNotEmpty()) {
                    itemsIndexed(
                        items = users,
                        key = { _, user -> user.id }
                    ) { index, user ->
                        if (index == 0) {
                            Spacer(Modifier.height(10.dp))
                        }

                        UserListCard(
                            onClick = {},
                            title = user.nickname,
                            leadIcon = user.icon
                        )

                        Spacer(Modifier.height(13.dp))
                    }
                }

                item { Spacer(Modifier.height(100.dp)) }
            }
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