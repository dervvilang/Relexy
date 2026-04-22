package com.example.relexy.domain.model

import androidx.annotation.DrawableRes

data class Dictionary(
    val id: String,
    val title: String,
    @DrawableRes val leadIcon: Int,
    val wordCount: Int,
    val progress: Int,
    val isOwnedByUser: Boolean
)
