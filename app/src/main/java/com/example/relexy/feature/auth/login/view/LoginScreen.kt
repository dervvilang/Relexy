package com.example.relexy.feature.auth.login.view

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.relexy.R
import com.example.relexy.core.ui.components.*
import com.example.relexy.core.ui.theme.RelexyTheme
import com.example.relexy.feature.learn.LearnMainScreen

@Composable
fun LoginScreen(
    onGoToRegister: () -> Unit,
    onLoginClick: () -> Unit
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) { detectTapGestures { focusManager.clearFocus() } }
            .padding(horizontal = 24.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(Modifier.height(24.dp))
            BrandTitle()
            Spacer(Modifier.height(10.dp))
            BrandIcon()
            Spacer(Modifier.height(34.dp))

            AuthCard(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(R.string.auth_login_title),
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(Modifier.height(26.dp))

                RelexyTextField(
                    label = stringResource(R.string.field_email),
                    value = email,
                    onValueChange = { email = it }
                )

                Spacer(Modifier.height(26.dp))

                RelexyTextField(
                    label = stringResource(R.string.field_password),
                    value = password,
                    onValueChange = { password = it },
                    isPassword = true
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = {}, contentPadding = PaddingValues(0.dp)) {
                        Text(
                            text = stringResource(R.string.action_forgot_password),
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }

                Spacer(Modifier.height(46.dp))

                PrimaryButton(
                    text = stringResource(R.string.action_login),
                    onClick = onLoginClick
                )

                TextButton(
                    onClick = onGoToRegister,
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        text = stringResource(R.string.action_create_account),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    RelexyTheme() {
        LoginScreen(
            onGoToRegister = {},
            onLoginClick = {}
        )
    }
}