package com.example.relexy.core.ui.components.textFields

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.example.relexy.R
import com.example.relexy.core.ui.theme.RelexyTheme

@Composable
fun SearchTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier
) {
    AppTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholder,
        modifier = modifier,
        leadingIconRes = R.drawable.ic_search_1,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        )
    )
}

@Preview
@Composable
fun SearchTextFieldP() {
    RelexyTheme() {
        var searchText by remember { mutableStateOf("") }
        SearchTextField(
            value = searchText,
            onValueChange = { searchText = it },
            placeholder = stringResource(R.string.dictionaries_search_hint)
        )
    }
}