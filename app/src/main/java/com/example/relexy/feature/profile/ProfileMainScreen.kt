package com.example.relexy.feature.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.relexy.R
import com.example.relexy.core.ui.components.DailyStatCard
import com.example.relexy.core.ui.components.ProfileBaseContainer
import com.example.relexy.core.ui.components.StreakStatCard
import com.example.relexy.core.ui.components.UserInfoCard
import com.example.relexy.core.ui.components.WeekDaysRow
import com.example.relexy.core.ui.theme.RelexyTheme

@Composable
fun ProfileMainScreen(
    onGoToSettings: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            onClick = onGoToSettings,
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.End)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_settings_1),
                contentDescription = "",
                modifier = Modifier.size(40.dp),
            )
        }

        Spacer(Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .size(130.dp)
                .border(
                    width = 1.dp, color = MaterialTheme.colorScheme.secondary,
                    shape = CircleShape
                )
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.ic_princess),
                contentDescription = null,
                modifier = Modifier.size(110.dp)
            )
        }

        Spacer(Modifier.height(13.dp))

        Text(
            text = stringResource(R.string.nickname_user, 1),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(Modifier.height(8.dp))

        UserInfoCard()

        Spacer(Modifier.height(14.dp))

        ProfileBaseContainer {
            WeekDaysRow()

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(26.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                StreakStatCard(
                    R.string.profile_current_streak,
                    R.string.period_n_days,
                    14,
                    modifier = Modifier.weight(1f)
                )

                StreakStatCard(
                    R.string.profile_record,
                    R.string.period_n_days,
                    19,
                    modifier = Modifier.weight(1f)
                )
            }

            DailyStatCard(5, 10, {})
        }


    }
}

@Preview
@Composable
fun ProfileMainScreenPreview() {
    RelexyTheme() {
        ProfileMainScreen()
    }
}